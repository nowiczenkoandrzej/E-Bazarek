package com.an.e_bazarek.feature_login.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginTextField(
    value: String,
    onValueChange: (text: String) -> Unit,
    title: String,
    isPassword: Boolean = false,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
) {

    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            fontFamily = FontFamily.SansSerif,
            fontSize = 16.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null
            )
            Box() {
                if(value.isBlank())
                    Text(
                        text = "Type your $title",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable { focusRequester.requestFocus() }
                    )
                BasicTextField(
                    value = if(isPassword)
                        displayPassword(value)
                    else
                        value,
                    onValueChange = { onValueChange(it) },
                    singleLine = true,
                    cursorBrush = SolidColor(Color.White),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .focusRequester(focusRequester),
                )

            }


        }
        Divider(
            modifier = Modifier.height(1.dp)
        )
    }
}

fun displayPassword(value: String) = "*".repeat(value.length)



/*
@Preview
@Composable
fun PasswordFieldPreview() {
    LoginTextField( isPassword = true)
}*/
