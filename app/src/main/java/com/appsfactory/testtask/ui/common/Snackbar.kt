package com.appsfactory.testtask.ui.common

import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.appsfactory.testtask.R

@Composable
fun Snackbar(snackbarHostState: SnackbarHostState) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = {
            androidx.compose.material.Snackbar(
                snackbarData = it,
                backgroundColor = colorResource(R.color.red),
                contentColor = colorResource(R.color.white)
            )
        }
    )
}