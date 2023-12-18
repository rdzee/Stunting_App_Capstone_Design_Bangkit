@file:Suppress("DEPRECATION")

package com.littlegrow.capstone_project.ui.components.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputItem(
    modifier: Modifier = Modifier,
    text: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    isError: Boolean = false,
    onTextChanged: (String) -> Unit,
    onNext: (KeyboardActionScope) -> Unit,
    onDone: (KeyboardActionScope) -> Unit,
) {
    var textInput by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = modifier.padding(4.dp))
        OutlinedTextField(
            value = textInput,
            onValueChange = {
                textInput = it
                onTextChanged(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = onDone,
                onNext = onNext
            ),
            singleLine = true,
            isError = isError,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorTextColor = Color.Red,
            ),
            modifier = modifier
                .fillMaxWidth()
                .border(BorderStroke(
                    width = 3.dp,
                    brush = Brush.horizontalGradient(
                        listOf(MaterialTheme.colorScheme.tertiary,
                            MaterialTheme.colorScheme.primary))),
                    shape = RoundedCornerShape(50)
                )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TextInputItemPreview() {
    Capstone_ProjectTheme {
        TextInputItem(
            text = "Name",
            onTextChanged = {},
            onDone = {},
            onNext = {}
        )
    }
}