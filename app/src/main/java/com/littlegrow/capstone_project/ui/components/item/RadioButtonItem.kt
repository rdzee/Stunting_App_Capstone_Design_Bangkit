package com.littlegrow.capstone_project.ui.components.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun RadioButtonItem(
    item1: String,
    item2: String,
    modifier: Modifier = Modifier
) {
    var selectedValue by remember { mutableStateOf("") }

    val isSelectedItem: (String) -> Boolean = { selectedValue == it }
    val onChangeState: (String) -> Unit = { selectedValue = it }

    val items = listOf(item1, item2)
    Column(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.gender),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            items.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .selectable(
                            selected = isSelectedItem(item),
                            onClick = { onChangeState(item) },
                            role = Role.RadioButton
                        )
                        .padding(8.dp)
                ) {
                    RadioButton(
                        selected = isSelectedItem(item),
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.surface
                        ),
                        onClick = null
                    )
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                        )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RadioButtonItemPreview() {
    Capstone_ProjectTheme {
        RadioButtonItem("Male", "Female")
    }
}