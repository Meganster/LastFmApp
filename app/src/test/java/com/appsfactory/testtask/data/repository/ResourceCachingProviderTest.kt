package com.appsfactory.testtask.data.repository

import com.appsfactory.testtask.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ResourceCachingProviderTest : BaseTest() {

    @Test
    fun `Test remoteDataProvider working correctly`() {
        val cache = ResourceCachingProvider<String>(
            databaseGetter = { flowOf(null) },
            databaseInserter = { flowOf(it) },
            remoteDataProvider = { flowOf(REMOTE_PROVIDER) }
        )

        runBlockingTest {
            cache.getCurrentValue().collect {
                assert(it == REMOTE_PROVIDER)
            }
        }
    }

    @Test
    fun `Test databaseGetter working correctly`() {
        val cache = ResourceCachingProvider<String>(
            databaseGetter = { flowOf(DATABASE_GETTER) },
            databaseInserter = { flowOf(it) },
            remoteDataProvider = { flowOf(REMOTE_PROVIDER) }
        )

        runBlockingTest {
            cache.getCurrentValue().collect {
                assert(it == DATABASE_GETTER)
            }
        }
    }

    companion object {
        const val REMOTE_PROVIDER = "remoteDataProvider"
        const val DATABASE_GETTER = "databaseGetter"
    }
}