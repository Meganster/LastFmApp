package com.appsfactory.testtask.ui.base.compose

import androidx.annotation.StringRes
import androidx.compose.material.SnackbarResult
import androidx.lifecycle.ViewModel
import com.appsfactory.testtask.R
import com.appsfactory.testtask.utils.Effect
import com.appsfactory.testtask.utils.StringWrapper
import com.appsfactory.testtask.utils.StringWrapper.StringResource
import timber.log.Timber
import java.io.Serializable

abstract class BaseComposeViewModel : ViewModel() {

    val showSnackbar = Effect<SnackbarDescription>()

    protected fun showSnackbar(description: SnackbarDescription) {
        showSnackbar.set(description)
    }

    protected fun showSnackbar(@StringRes resource: Int, vararg args: Any) {
        showSnackbar.set(SnackbarDescription(StringResource(resource, *args)))
    }

    open fun onErrorHappened(error: Throwable) {
        Timber.e(error)
        showSnackbar(R.string.something_went_wrong)
    }

    data class SnackbarDescription(
        val text: StringWrapper,
        val buttonTitle: StringWrapper? = null,
        val onSnackbarAction: ((SnackbarResult) -> Unit)? = null
    ) : Serializable
}