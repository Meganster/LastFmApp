package com.appsfactory.testtask

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

open class BaseTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    protected val testDispatcher = coroutinesTestRule.testDispatcher

    @OptIn(ExperimentalCoroutinesApi::class)
    protected fun runBlockingTest(block: suspend TestScope.() -> Unit) = testDispatcher.run { runTest { block() } }

    @Before
    fun setupDefault() {
        MockitoAnnotations.openMocks(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun settle() {
        testDispatcher.scheduler.advanceUntilIdle()
    }
}