package com.littlegrow.capstone_project.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.viewmodel.compose.viewModel
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.model.InputEvent
import com.littlegrow.capstone_project.model.ValidationEvent
import com.littlegrow.capstone_project.ui.components.item.InputHeaderItem
import com.littlegrow.capstone_project.ui.components.item.RadioButtonItem
import com.littlegrow.capstone_project.ui.components.item.TextInputItem
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme
import com.littlegrow.capstone_project.ui.viewmodel.InputDataViewModel

@Composable
fun InputDataScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    inputDataViewModel: InputDataViewModel = viewModel()
) {
    val state = inputDataViewModel.uiState.value
    val context = LocalContext.current
    val localFocus = LocalFocusManager.current
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
    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier.padding(8.dp)
    ) {
        item {
            InputHeaderItem(
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
                text = stringResource(id = R.string.age),
                hint = stringResource(id = R.string.age_hint),
                keyboardType = KeyboardType.Number,
                onTextChanged = { inputDataViewModel.onEvent(InputEvent.AgeChanged(it.toInt())) },
                isError = state.ageError,
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
                modifier.padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Submit")
            }
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