package com.appsfactory.testtask.ui.base

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.appsfactory.testtask.utils.Event
import com.appsfactory.testtask.utils.StringWrapper
import com.appsfactory.testtask.utils.StringWrapper.StringResource
import com.appsfactory.testtask.utils.StringWrapper.Text
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    protected val _showSnackBar = MutableStateFlow<Event<StringWrapper>?>(null)
    val showSnackbar: StateFlow<Event<StringWrapper>?> = _showSnackBar

    protected fun showSnackbar(@StringRes resource: Int) {
        _showSnackBar.value = Event(StringResource(resource))
    }

    protected fun showSnackbar(text: String) {
        _showSnackBar.value = Event(Text(text))
    }
}