package com.appsfactory.testtask.utils

import android.view.View
import androidx.core.content.ContextCompat
import com.appsfactory.testtask.R
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

internal fun View.makeSnackbar(text: String, anchorView: View? = null): Snackbar {
    val snackbar = Snackbar.make(this, text, Snackbar.LENGTH_LONG)

    snackbar.view.apply {
        backgroundTintList = ContextCompat.getColorStateList(context, R.color.red)
    }

    anchorView?.let {
        snackbar.anchorView = it
    }

    return snackbar
}

internal fun <T, R> Iterable<T>.mapIgnoreErrors(transform: (T) -> R): List<R> {
    return mapNotNull {
        try {
            transform.invoke(it)
        } catch (e: Exception) {
            Timber.e("While mapIgnoreErrors catch error: $e")
            null
        }
    }
}