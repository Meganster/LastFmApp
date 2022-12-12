package com.appsfactory.testtask

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> TestScope.observeAndCollect(flow: Flow<T>, list: MutableList<T>): Job = launch(UnconfinedTestDispatcher(testScheduler)) {
    flow.collect {
        list.add(it)
    }
}