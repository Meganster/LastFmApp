package com.appsfactory.testtask.ui.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appsfactory.testtask.utils.Event
import com.appsfactory.testtask.utils.StringWrapper
import com.appsfactory.testtask.utils.StringWrapper.StringResource
import com.appsfactory.testtask.utils.StringWrapper.Text

abstract class BaseViewModel : ViewModel() {

    protected val _showSnackBar = MutableLiveData<Event<StringWrapper>>()
    val showSnackbar: LiveData<Event<StringWrapper>> = _showSnackBar

    protected fun showSnackbar(@StringRes resource: Int) {
        _showSnackBar.postValue(Event(StringResource(resource)))
    }

    protected fun showSnackbar(text: String) {
        _showSnackBar.postValue(Event(Text(text)))
    }
}