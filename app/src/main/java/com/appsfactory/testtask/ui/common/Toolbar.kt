package com.appsfactory.testtask.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.appsfactory.testtask.R.color

@Composable
fun Toolbar(title: String) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                maxLines = 1
            )
        },
        backgroundColor = colorResource(color.colorPrimary),
        contentColor = colorResource(color.black)
    )
}