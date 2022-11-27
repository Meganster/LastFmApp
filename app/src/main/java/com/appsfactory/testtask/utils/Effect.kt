package com.appsfactory.testtask.utils

import androidx.annotation.GuardedBy
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@Stable
abstract class BaseEffect<T : Any> {
    @GuardedBy("mutex") protected var value: T? = null

    @GuardedBy("mutex") protected val consumed = mutableStateOf(true)

    private val mutex: Mutex = Mutex()

    @Composable
    fun Handler(block: CoroutineScope.(T) -> Unit) {
        val blockScope = rememberCoroutineScope()
        // We assume reading from consumed.value is safe
        LaunchedEffect(key1 = consumed.value) {
            mutex.withLock {
                if (consumed.value) return@LaunchedEffect
                blockScope.block(value!!) // TODO: what if it blocks? Equality policy?
                consumed.value = true
            }
        }
    }

    protected open fun set(value: T) {
        runBlocking {
            mutex.withLock {
                this@BaseEffect.value = value
                consumed.value = false
            }
        }
    }
}

@Stable
class Effect<T : Any> : BaseEffect<T>() {
    public override fun set(value: T) = super.set(value)

    @VisibleForTesting
    val lastValue
        get() = value?.takeIf { !consumed.value }
}

@Stable
class SimpleEffect : BaseEffect<Unit>() {
    fun invoke() = set(Unit)

    @VisibleForTesting
    fun isInvoked() = value != null && !consumed.value
}