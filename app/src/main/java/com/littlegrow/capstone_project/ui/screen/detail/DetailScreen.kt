package com.littlegrow.capstone_project.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun DetailScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isEdit by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var textInput by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("14.2") }
    var height by remember { mutableStateOf("80") }
    val diseaseHistory = remember { mutableStateListOf<String>() }

    val ageDisplay = stringResource(id = R.string.age_display, 2, 11)

    DetailContent(
        isEdit = isEdit,
        name = "John Doe",
        age = ageDisplay,
        gender = "Laki - Laki",
        weight = weight,
        height = height,
        textInput = textInput,
        diseaseHistory = diseaseHistory,
        showDeleteDialog = showDeleteDialog,
        setShowDeleteDialog = { showDeleteDialog = it },
        onWeightChange = { weight = it },
        onHeightChange = { height = it },
        onAddDiseaseChange = { textInput = it },
        setEdit = { isEdit = it },
        addDisease = { diseaseHistory.add(it) },
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DetailContent(
    name: String,
    age: String,
    gender: String,
    weight: String,
    height: String,
    diseaseHistory: List<String>,
    isEdit: Boolean,
    textInput: String,
    showDeleteDialog: Boolean,
    setShowDeleteDialog: (Boolean) -> Unit,
    onHeightChange: (String) -> Unit,
    onWeightChange: (String) -> Unit,
    onAddDiseaseChange: (String) -> Unit,
    addDisease: (String) -> Unit,
    setEdit: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = FocusRequester()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    if (isEdit) {
                        IconButton(
                            onClick = {
                                setEdit(false)
                                // TODO : Set Data Back to Before Edit
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(id = R.string.close_edit),
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    } else {
                        IconButton(
                            onClick = onBackClick
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = stringResource(id = R.string.back),
                                modifier = Modifier
                                    .size(32.dp)
                            )
                        }
                    }
                },
                actions = {
                    if (isEdit) {
                        IconButton(
                            onClick = {
                                // TODO : Upload update
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = stringResource(id = R.string.save_data),
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                setEdit(true)
                                focusRequester.requestFocus()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = stringResource(id = R.string.edit_data),
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }

                        IconButton(
                            onClick = {
                                setShowDeleteDialog(true)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = stringResource(id = R.string.delete_data),
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        if (showDeleteDialog) {
            DeleteDialog(
                setShowDeleteDialog = setShowDeleteDialog
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            DetailData(
                title = stringResource(id = R.string.name),
                data = name
            )
            DetailData(
                title = stringResource(id = R.string.age),
                data = age
            )
            DetailData(
                title = stringResource(id = R.string.gender),
                data = gender
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DetailInput(
                    title = stringResource(id = R.string.weight),
                    inputValue = weight,
                    isEdit = isEdit,
                    onInputChange = onWeightChange,
                    modifier = Modifier
                        .focusRequester(focusRequester)
                )
                DetailInput(
                    title = stringResource(id = R.string.height),
                    inputValue = height,
                    isEdit = isEdit,
                    onInputChange = onHeightChange
                )
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.disesaseHistory),
                    style = MaterialTheme.typography.bodySmall,
                )
                if (diseaseHistory.isEmpty()) {
                    Text(
                        text = "-",
                    )
                }
                FlowRow(
                    maxItemsInEachRow = 4
                ) {
                    diseaseHistory.mapIndexed { index, disease ->
                        Text(
                            text = "${disease}${if (index != diseaseHistory.lastIndex) ", " else ""}",
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 18.sp
                        )
                    }
                }
            }
            if (isEdit) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                ) {
                    TextField(
                        value = textInput,
                        label = {
                            Text(
                                text = stringResource(id = R.string.add_disease_hint),
                                style = MaterialTheme.typography.bodySmall
                            )
                        },
                        onValueChange = {
                            onAddDiseaseChange(it)
                        },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .width(200.dp)
                    )

                    Button(
                        enabled = textInput.isNotBlank(),
                        onClick = {
                            addDisease(textInput)
                            onAddDiseaseChange("")
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.add_disease)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailData(
    title: String,
    data: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = data,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp
        )
    }
}

@Composable
fun DetailInput(
    title: String,
    inputValue: String,
    isEdit: Boolean,
    onInputChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .padding(horizontal = 16.dp)
            .width(100.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall
        )
        TextField(
            readOnly = !isEdit,
            value = inputValue,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            textStyle = TextStyle(
                textAlign = TextAlign.Center
            ),
            onValueChange = onInputChange
        )
    }
}

@Composable
fun DeleteDialog(
    setShowDeleteDialog: (Boolean) -> Unit
){
    AlertDialog(
        onDismissRequest = { setShowDeleteDialog(false) },
        title = {
            Text(
                text = stringResource(id = R.string.delete_title)
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.delete_warning)
            )
        },
        dismissButton = {
            TextButton(
                onClick = {
                    setShowDeleteDialog(false)
                }
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    // TODO : Delete Profile
                }
            ) {
                Text(text = stringResource(id = R.string.delete_data))
            }
        }
    )
}

@Composable
@Preview(
    showBackground = true
)
fun DeleteDialogPreview() {
    Capstone_ProjectTheme {
        DeleteDialog(
            setShowDeleteDialog = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    device = Devices.DEFAULT,
    showSystemUi = true
)
fun DetailContentPreview() {
    Capstone_ProjectTheme {
        DetailContent(
            name = "John Doe",
            age = "2 tahun 11 bulan",
            gender = "Laki - Laki",
            weight = "14.2",
            height = "80",
            diseaseHistory = listOf(
                "DBD",
                "Flu",
                "DBD"
            ),
            isEdit = false,
            textInput = "",
            setEdit = {},
            onBackClick = {},
            addDisease = {},
            onAddDiseaseChange = {},
            onHeightChange = {},
            onWeightChange = {},
            showDeleteDialog = false,
            setShowDeleteDialog = {}
        )
    }
}