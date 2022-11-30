package com.appsfactory.testtask.ui.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appsfactory.testtask.R

@Composable
fun SearchCell(
    modifier: Modifier,
    searchValue: TextFieldValue,
    onSearchChanged: (TextFieldValue) -> Unit,
    onStartSearchClicked: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = modifier,
        value = searchValue,
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        placeholder = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.search_placeholder),
                color = Color.Gray,
                fontSize = 16.sp
            )
        },
        onValueChange = {
            onSearchChanged.invoke(it)
        },
        trailingIcon = {
            if (searchValue != TextFieldValue("")) {
                IconButton(
                    onClick = { onStartSearchClicked.invoke() }
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_button)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        keyboardActions = KeyboardActions(onSearch = {
            onStartSearchClicked.invoke()
            focusManager.clearFocus()
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            disabledIndicatorColor = Color.Transparent
        )
    )
}