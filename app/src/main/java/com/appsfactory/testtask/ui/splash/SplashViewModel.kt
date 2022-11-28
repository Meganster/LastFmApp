package com.appsfactory.testtask.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.appsfactory.testtask.R
import com.appsfactory.testtask.data.repository.ArtistsRepository
import com.appsfactory.testtask.ui.base.BaseViewModel
import com.appsfactory.testtask.utils.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import timber.log.Timber
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val authRepository: ArtistsRepository
) : BaseViewModel() {

    private val _isAuthSucceeded = MutableLiveData<Event<Unit>>()
    val isAuthSucceeded: LiveData<Event<Unit>> = _isAuthSucceeded

    private val defaultExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable, "Error when loading SplashViewModel")
        showSnackbar(R.string.something_went_wrong)
    }
    private val defaultScope = viewModelScope + defaultExceptionHandler

    fun completeAuth() {
        defaultScope.launch {
            delay(1000) // TODO maybe here should be getMobileSession
            _isAuthSucceeded.value = Event(Unit)
        }
    }
}