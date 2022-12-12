package com.appsfactory.testtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.junit.Assert
import kotlin.coroutines.resume

const val TIMEOUT_DEFAULT = 3000L

suspend fun <T> LiveData<T>.awaitAny(timeout: Long = TIMEOUT_DEFAULT) = awaitCondition(timeout, false) { true }

suspend fun <T> LiveData<T>.await(value: T?, timeout: Long = TIMEOUT_DEFAULT) = awaitCondition(timeout) { it == value }

suspend fun <T> LiveData<T>.awaitNot(value: T?, timeout: Long = TIMEOUT_DEFAULT) = awaitCondition(timeout) { it != value }

suspend fun <T> LiveData<T>.awaitCondition(timeout: Long = TIMEOUT_DEFAULT, checkExistingValue: Boolean = true, predicate: (T?) -> Boolean): T? {
    // Capturing a stack trace each time is expensive, but if something goes wrong, we'll have a full stack trace, which is convenient for tests
    val stackTrace = Thread.currentThread().stackTrace
    val receivedValuesCollector = mutableListOf<T?>()
    return try {
        withContext(Dispatchers.Main) {
            if (checkExistingValue) {
                receivedValuesCollector.add(value)
                if (predicate(value)) return@withContext value
            }

            withTimeout(timeout) {
                observeUntil(receivedValuesCollector, predicate)
            }
        }
    } catch (ex: Exception) {
        throw AssertionError("Waiting for condition failed, values seen so far: $receivedValuesCollector").apply {
            setStackTrace(stackTrace)
            initCause(ex)
        }
    }
}

private suspend fun <T> LiveData<T>.observeUntil(receivedValuesCollector: MutableList<T?>, predicate: (T?) -> Boolean): T? =
    suspendCancellableCoroutine { continuation ->
        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                receivedValuesCollector.add(t)
                if (!predicate(t)) return

                removeObserver(this)
                continuation.resume(value)
            }
        }
        observeForever(observer)
    }

suspend fun LiveData<*>.awaitNothingWhileRunning(timeout: Long = TIMEOUT_DEFAULT, block: suspend () -> Unit) {
    val verifier = EventSequenceVerifier(this)
    val result = runCatching {
        verifier.runAndAwait(block, timeout) { it.size > 1 }
    }
    if (result.isSuccess) throw AssertionError("Expected no events, but seen the following values: ${verifier.receivedValues}")
}

suspend fun <T> LiveData<T>.awaitSequenceWhileRunning(vararg values: T?, timeout: Long = TIMEOUT_DEFAULT, block: suspend () -> Unit) {
    EventSequenceVerifier(this).runAndAwait(block, timeout) { receivedValues ->
        (receivedValues.size >= values.size).also { enoughValues ->
            if (enoughValues) Assert.assertEquals(values.toList(), receivedValues.subList(0, values.size))
        }
    }
}

class EventSequenceVerifier<T>(private val liveData: LiveData<T>) {
    private val receivedValuesMutable = mutableListOf<T?>()
    val receivedValues: List<T?> = receivedValuesMutable

    private var waitingContinuation: CancellableContinuation<Unit>? = null
    private val observer = Observer<T> {
        receivedValuesMutable.add(it)
        waitingContinuation?.resume(Unit)
    }

    private suspend fun startListening() = withContext(Dispatchers.Main) {
        receivedValuesMutable.add(liveData.value)
        liveData.observeForever(observer)
    }

    private suspend fun awaitSequenceMatch(timeout: Long = TIMEOUT_DEFAULT, matcher: (List<T?>) -> Boolean) = withContext(Dispatchers.Main) {
        val stackTrace = Thread.currentThread().stackTrace
        try {
            withTimeout(timeout) {
                while (!matcher(receivedValuesMutable)) {
                    suspendCancellableCoroutine<Unit> {
                        waitingContinuation = it
                    }
                    waitingContinuation = null
                }
            }
        } catch (ex: Exception) {
            throw AssertionError("Waiting for sequence failed, values received so far: $receivedValuesMutable").apply {
                setStackTrace(stackTrace)
                initCause(ex)
            }
        } finally {
            liveData.removeObserver(observer)
        }
    }

    suspend fun runAndAwait(block: suspend () -> Unit, timeout: Long = TIMEOUT_DEFAULT, matcher: (List<T?>) -> Boolean) {
        startListening()
        try {
            block()
        } finally {
            awaitSequenceMatch(timeout, matcher)
        }
    }
}

fun <T> LiveData<T>.observeAndCollect(): List<T> = mutableListOf<T>().also { observeForever(it::add) }