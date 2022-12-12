package com.appsfactory.testtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.junit.Assert
import org.mockito.Mockito

/**
 * Verify LiveData
 *
 * val stubValue = 12
 * verifyLiveData<Int> {
 *     given { stubValue }
 *     with { rateViewModel.liveDataField }
 *     `when` { rateViewModel.doActionWithLiveData(it) }
 *     then { stubValue } or then { argThat { it == stubValue } }
 * }
 *
 * or
 *
 * verifyLiveData<Int> {
 *     with { rateViewModel.liveDataField }
 *     then { stubValue } or then { argThat { it == stubValue } }
 * }
 * */

class TestLiveData<T> {
    var observer: Observer<T>? = mockAny()
    var liveData: LiveData<T>? = null
    var data: T? = null
}

fun <T> TestLiveData<T>.given(block: TestLiveData<T>.() -> T) {
    data = block()
}

fun <T> TestLiveData<T>.with(block: TestLiveData<T>.() -> LiveData<T>) {
    liveData = block()
}

fun <T> TestLiveData<T>.`when`(block: TestLiveData<T>.(T) -> Unit) {
    Assert.assertNotNull(data)
    data?.apply { block(this) }
}

fun <T> TestLiveData<T>.whenNullable(block: TestLiveData<T>.(T?) -> Unit) {
    block(data)
}

fun <T> TestLiveData<T>.then(block: TestLiveData<T>.(T?) -> T?) {
    Assert.assertNotNull(liveData)
    Assert.assertNotNull(observer)

    observer?.apply {
        liveData?.observeForever(this)
        Mockito.verify(this).onChanged(block(data))
    }
}

inline fun <reified T> verifyLiveData(done: TestLiveData<T>.() -> Unit) = TestLiveData<T>().run(done)