package com.appsfactory.testtask.ui.base.compose

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.appsfactory.testtask.R
import com.appsfactory.testtask.utils.Effect
import com.appsfactory.testtask.utils.StringWrapper
import com.appsfactory.testtask.utils.StringWrapper.StringResource
import com.appsfactory.testtask.utils.StringWrapper.Text
import timber.log.Timber

abstract class BaseComposeViewModel : ViewModel() {

    val showSnackbar = Effect<StringWrapper>()

    protected fun showSnackbar(@StringRes resource: Int, vararg args: Any) {
        showSnackbar.set(StringResource(resource, *args))
    }

    protected fun showSnackbar(text: String) {
        showSnackbar.set(Text(text))
    }

    open fun onErrorHappened(error: Throwable) {
        Timber.e(error)
        showSnackbar(R.string.something_went_wrong)
    }
}