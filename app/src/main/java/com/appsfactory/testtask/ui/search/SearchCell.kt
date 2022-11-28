package com.appsfactory.testtask.ui.search

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appsfactory.testtask.R

@Composable
fun SearchCell(
    modifier: Modifier,
    searchValue: TextFieldValue,
    onSearchChanged: (TextFieldValue) -> Unit
) {
    TextField(
        modifier = modifier,
        value = searchValue,
        onValueChange = onSearchChanged,
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp),
                imageVector = Icons.Default.Search,
                contentDescription = ""
            )
        },
        trailingIcon = {
            if (searchValue != TextFieldValue("")) {
                IconButton(
                    onClick = { onSearchChanged.invoke(TextFieldValue("")) }
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp),
                        imageVector = Icons.Default.Close,
                        contentDescription = ""
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = colorResource(R.color.colorPrimary),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}