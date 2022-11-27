package com.appsfactory.testtask.ui.base.compose

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.appsfactory.testtask.utils.Effect
import com.appsfactory.testtask.utils.StringWrapper
import com.appsfactory.testtask.utils.StringWrapper.StringResource
import com.appsfactory.testtask.utils.StringWrapper.Text

abstract class BaseComposeViewModel : ViewModel() {

    val showSnackbar = Effect<StringWrapper>()

    protected fun showSnackbar(@StringRes resource: Int) {
        showSnackbar.set(StringResource(resource))
    }

    protected fun showSnackbar(text: String) {
        showSnackbar.set(Text(text))
    }
}