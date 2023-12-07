package com.littlegrow.capstone_project.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.viewmodel.compose.viewModel
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.model.InputEvent
import com.littlegrow.capstone_project.model.ValidationEvent
import com.littlegrow.capstone_project.ui.components.item.HeaderItem
import com.littlegrow.capstone_project.ui.components.item.RadioButtonItem
import com.littlegrow.capstone_project.ui.components.item.TextInputItem
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme
import com.littlegrow.capstone_project.ui.viewmodel.InputDataViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDataScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    inputDataViewModel: InputDataViewModel = viewModel()
) {
    val state = inputDataViewModel.uiState.value
    val context = LocalContext.current
    val localFocus = LocalFocusManager.current

    val stateDatePicker = rememberDatePickerState()
    var isDialogOpen by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = context) {
        inputDataViewModel.validationEvent.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    Toast
                        .makeText(context, getString(context, R.string.success), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderItem(
            title = stringResource(id = R.string.input_data),
            onBackClick = onBackClick
        )
        TextInputItem(
            text = stringResource(id = R.string.name),
            hint = stringResource(id = R.string.name_hint),
            onTextChanged = { inputDataViewModel.onEvent(InputEvent.NameChanged(it)) },
            isError = state.nameError,
            onNext = { localFocus.moveFocus(FocusDirection.Down) },
            onDone = {},
            modifier = modifier.fillMaxWidth()
        )
        Column {
            Text(
                text = stringResource(id = R.string.birth_date),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 4.dp)
            )

            TextField(
                readOnly = true,
                value = inputDataViewModel.formattedDate.value,
                label = {
                    Text(text = stringResource(id = R.string.age_hint), style = MaterialTheme.typography.bodySmall)
                },
                onValueChange = {},
                isError = state.birthDateError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .onFocusChanged {
                        if (it.isFocused) {
                            isDialogOpen = true
                        }
                    }
            )
        }

        if (isDialogOpen) {
            DatePickerDialog(
                onDismissRequest = {
                    isDialogOpen = false
                    localFocus.clearFocus()
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            isDialogOpen = false
                            localFocus.clearFocus()
                            stateDatePicker.selectedDateMillis?.let {
                                InputEvent.BirthDateChanged(
                                    it
                                )
                            }?.let { inputDataViewModel.onEvent(it) }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.okay))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            isDialogOpen = false
                            localFocus.clearFocus()
                        }
                    ) {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                }
            )
            {
                DatePicker(
                    state = stateDatePicker,
                    dateValidator = {
                        it <= System.currentTimeMillis()
                    },
                    showModeToggle = false
                )
            }
        }

        RadioButtonItem(
            item1 = stringResource(id = R.string.male),
            item2 = stringResource(id = R.string.female)
        )
        TextInputItem(
            text = stringResource(id = R.string.weight),
            hint = stringResource(id = R.string.weight_hint),
            keyboardType = KeyboardType.Number,
            onTextChanged = { inputDataViewModel.onEvent(InputEvent.WeightChanged(it)) },
            isError = state.weightError,
            onNext = { localFocus.moveFocus(FocusDirection.Down) },
            onDone = {}
        )
        TextInputItem(
            text = stringResource(id = R.string.height),
            hint = stringResource(id = R.string.height_hint),
            keyboardType = KeyboardType.Number,
            onTextChanged = { inputDataViewModel.onEvent(InputEvent.HeightChanged(it)) },
            isError = state.heightError,
            onNext = { localFocus.moveFocus(FocusDirection.Down) },
            onDone = {}
        )
        TextInputItem(
            text = stringResource(id = R.string.disesaseHistory),
            hint = stringResource(id = R.string.disesaseHistory_hint),
            onTextChanged = { inputDataViewModel.onEvent(InputEvent.DiseaseHistoryChanged(it)) },
            isError = state.diseaseHistoryError,
            onNext = { localFocus.moveFocus(FocusDirection.Down) },
            onDone = {}
        )
        TextInputItem(
            text = stringResource(id = R.string.birthDistance),
            hint = stringResource(id = R.string.birthDistance_hint),
            onTextChanged = { inputDataViewModel.onEvent(InputEvent.BirthDistanceChanged(it)) },
            isError = state.birthDistanceError,
            onNext = { localFocus.moveFocus(FocusDirection.Down) },
            onDone = {}
        )
        Spacer(modifier = modifier.height(8.dp))
        Button(
            onClick = {
                inputDataViewModel.onEvent(InputEvent.Submit)
            },
            modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InputDataScreenPreview() {
    Capstone_ProjectTheme {
        InputDataScreen(
            onBackClick = {}
        )
    }
}