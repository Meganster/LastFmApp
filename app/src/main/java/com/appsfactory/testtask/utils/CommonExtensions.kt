package com.appsfactory.testtask.utils

import android.view.View
import androidx.core.content.ContextCompat
import com.appsfactory.testtask.R
import com.google.android.material.snackbar.Snackbar

internal fun View.makeSnackbar(text: String, isError: Boolean, anchorView: View? = null): Snackbar {
    val snackbar = Snackbar.make(this, text, Snackbar.LENGTH_LONG)

    snackbar.view.apply {
        if (isError) {
            backgroundTintList = ContextCompat.getColorStateList(context, R.color.colorAccent)
        }
    }

    anchorView?.let {
        snackbar.anchorView = it
    }

    return snackbar
}