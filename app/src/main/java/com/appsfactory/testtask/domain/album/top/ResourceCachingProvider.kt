package com.appsfactory.testtask.domain.album.top

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class)
internal class ResourceCachingProvider<T : Any>(
    private val databaseInserter: (T) -> Flow<T>,
    private val databaseGetter: () -> Flow<T?>,
    private val remoteDataProvider: () -> Flow<T>
) {
    private var result: MutableStateFlow<T>? = null

    enum class State {
        UNINITIALIZED,
        FULL,
        EMPTY
    }

    @Volatile
    private var shouldAskForUpdate = false

    @Volatile
    private var cachedDataState: State = State.UNINITIALIZED

    private fun askForContentUpdate() = result ?: requestNewResult()

    private fun requestNewResult(): Flow<T> {
        return remoteDataProvider()
            .flowOn(Dispatchers.IO)
            .flatMapLatest {
                val item = databaseInserter(it)
                cachedDataState = State.FULL
                item
            }
    }

    fun invalidateCachedData() {
        shouldAskForUpdate = true
    }

    fun clearState() {
        cachedDataState = State.EMPTY
    }

    fun getCurrentValue(invalidateCache: Boolean = false): Flow<T> {
        return if (invalidateCache or shouldAskForUpdate) {
            askForContentUpdate()
        } else {
            if (cachedDataState == State.FULL) {
                databaseGetter()
                    .flowOn(Dispatchers.IO)
                    .map { it!! }
            } else {
                askForContentUpdate()
            }
        }
    }
}