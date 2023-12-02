package com.littlegrow.capstone_project.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun TextInputItem(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
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
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = modifier.padding(4.dp))
        TextField(
            value = textInput,
            onValueChange = {
                textInput = it
                onTextChanged(it)
            },
            label = {
                Text(text = hint, style = MaterialTheme.typography.bodySmall)
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
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TextInputItemPreview() {
    Capstone_ProjectTheme {
        TextInputItem(
            text = "Name",
            hint = "Name",
            onTextChanged = {},
            onDone = {},
            onNext = {}
        )
    }
}