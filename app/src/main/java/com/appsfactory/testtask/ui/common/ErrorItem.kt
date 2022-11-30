package com.appsfactory.testtask.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appsfactory.testtask.R

@Composable
fun ErrorItem(message: String) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.red))
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .align(Alignment.CenterVertically),
                color = Color.White,
                text = message,
                fontSize = 16.sp
            )
        }
    }
}