package com.littlegrow.capstone_project.ui.screen.detail

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.di.Injection
import com.littlegrow.capstone_project.model.DetailDataResponse
import com.littlegrow.capstone_project.model.UpdateBodyData
import com.littlegrow.capstone_project.ui.screen.Result
import com.littlegrow.capstone_project.ui.screen.ViewModelFactory
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme
import java.util.Calendar

@Composable
fun DetailScreen(
    profileId: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepo(context))
    )
) {
    var databaseData: DetailDataResponse? by remember { mutableStateOf(null) }
    var isEdit by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var textInput by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    val diseaseHistory = remember { mutableStateListOf<String>() }
    var age by remember { mutableStateOf("") }
    var lingkarKepala by remember { mutableStateOf("") }
    var lingkarLengan by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }

    viewModel.profileDetail.collectAsState(initial = Result.Loading).value.let { result ->
        when (result) {
            is Result.Loading -> {
                viewModel.getProfileData(profileId)
                loading = true
            }

            is Result.Success -> {
                LaunchedEffect(key1 = Unit) {
                    loading = false
                    val res = result.data
                    databaseData = res
                    gender = if (res.jenisKelamin == "Laki") {
                        context.getString(R.string.male)
                    } else {
                        context.getString(R.string.female)
                    }
                    name = res.namaAnak
                    age = getAge(context = context, birth = res.umur)
                    weight = res.beratBadan.toString()
                    height = res.tinggiBadan.toString()
                    lingkarKepala = res.lingkarKepala.toString()
                    lingkarLengan = res.lingkarLengan.toString()
                    val disease = res.riwayatPenyakit.replace(" ", "").split(",")
                    disease.forEach {
                        if (it != "-") {
                            diseaseHistory.add(it)
                        }
                    }
                }
            }

            is Result.Error -> {
                loading = false
                Log.d("DetailScreen: ", result.error)
                Toast.makeText(
                    LocalContext.current,
                    "DetailScreen: ${result.error}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    viewModel.updateMessage.collectAsState().value.let { result ->
        when (result) {
            is Result.Loading -> {}
            is Result.Success -> {
                LaunchedEffect(key1 = Unit) {
                    loading = false
                    Toast.makeText(
                        context,
                        result.data.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    onBackClick()
                }
            }

            is Result.Error -> {
                loading = false
                Toast.makeText(
                    context,
                    "UpdateData: ${result.error}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    viewModel.deleteMessage.collectAsState().value.let { result ->
        when (result) {
            is Result.Loading -> {}
            is Result.Success -> {
                Log.v("Detail", "masuk")
                loading = false
                showDeleteDialog = false
                Toast.makeText(
                    context,
                    result.data.message,
                    Toast.LENGTH_SHORT
                ).show()
                onBackClick()
            }

            is Result.Error -> {
                loading = false
                Toast.makeText(
                    context,
                    "UpdateData: ${result.error}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    DetailContent(
        loading = loading,
        isEdit = isEdit,
        name = name,
        age = age,
        gender = gender,
        weight = weight,
        height = height,
        lingkarLengan = lingkarLengan,
        lingkarKepala = lingkarKepala,
        textInput = textInput,
        diseaseHistory = diseaseHistory,
        showDeleteDialog = showDeleteDialog,
        setShowDeleteDialog = { showDeleteDialog = it },
        onWeightChange = { weight = it },
        onHeightChange = { height = it },
        onLingkarLenganChanged = { lingkarLengan = it },
        onLingkarKepalaChanged = { lingkarKepala = it },
        onAddDiseaseChange = { textInput = it },
        setEdit = {
            isEdit = it
            if (!isEdit) {
                weight = databaseData?.beratBadan.toString()
                height = databaseData?.tinggiBadan.toString()
                lingkarLengan = databaseData?.lingkarLengan.toString()
                lingkarKepala = databaseData?.lingkarKepala.toString()
                diseaseHistory.clear()
                val disease = databaseData?.riwayatPenyakit?.replace(" ", "")?.split(",")
                disease?.forEach { diseaseItem ->
                    if (diseaseItem != "-") {
                        diseaseHistory.add(diseaseItem)
                    }
                }
            }
        },
        addDisease = { diseaseHistory.add(it) },
        onDeleteClicked = {
            viewModel.deleteProfile(profileId)
        },
        onUpdateClicked = {
            val updateData = UpdateBodyData(
                berat_badan = weight.toDouble(),
                tinggi_badan = height.toDouble(),
                riwayat_penyakit = diseaseHistory.joinToString(", "),
                lingkar_kepala = lingkarKepala.toDouble(),
                lingkar_lengan = lingkarLengan.toDouble()
            )
            viewModel.updateProfile(
                profileId = profileId,
                updateData = updateData
            )
            loading = true
        },
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DetailContent(
    loading: Boolean,
    name: String,
    age: String,
    gender: String,
    weight: String,
    height: String,
    lingkarKepala: String,
    lingkarLengan: String,
    diseaseHistory: List<String>,
    isEdit: Boolean,
    textInput: String,
    showDeleteDialog: Boolean,
    setShowDeleteDialog: (Boolean) -> Unit,
    onHeightChange: (String) -> Unit,
    onWeightChange: (String) -> Unit,
    onLingkarKepalaChanged: (String) -> Unit,
    onLingkarLenganChanged: (String) -> Unit,
    onAddDiseaseChange: (String) -> Unit,
    onDeleteClicked: () -> Unit,
    onUpdateClicked: () -> Unit,
    addDisease: (String) -> Unit,
    setEdit: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = FocusRequester()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.detail_profile),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    if (isEdit) {
                        IconButton(
                            onClick = {
                                setEdit(false)
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
                                tint = Color.White,
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
                                onUpdateClicked()
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
                                tint = Color.White,
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
                                tint = Color.White,
                                contentDescription = stringResource(id = R.string.delete_data),
                                modifier = modifier
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
                setShowDeleteDialog = setShowDeleteDialog,
                onDeleteClicked = onDeleteClicked
            )
        }

        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = modifier.padding(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.detail_image),
                    contentDescription = null,
                    modifier = modifier
                        .padding(bottom = 4.dp)
                        .height(200.dp)
                )
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
                    modifier = modifier.fillMaxWidth()
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
                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    DetailInput(
                        title = stringResource(id = R.string.lingkarLengan),
                        inputValue = lingkarLengan,
                        isEdit = isEdit,
                        onInputChange = onLingkarLenganChanged,
                    )
                    DetailInput(
                        title = stringResource(id = R.string.lingkarKepala),
                        inputValue = lingkarKepala,
                        isEdit = isEdit,
                        onInputChange = onLingkarKepalaChanged
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.disesaseHistory),
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primary
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
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
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

            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

fun getAge(context: Context, birth: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = birth
    val year = calendar.get(Calendar.YEAR) - 1970
    val month = calendar.get(Calendar.MONTH)
    return context.getString(R.string.age_display, year.toString(), month.toString())
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
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary

        )
        Text(
            text = data,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
    }
}

@Composable
fun DetailInput(
    title: String,
    inputValue: String,
    isEdit: Boolean,
    onInputChange: (String) -> Unit,
    modifier: Modifier = Modifier,
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
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        )
        TextField(
            readOnly = !isEdit,
            value = inputValue,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            ),
            onValueChange = onInputChange,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
            )
        )
    }
}

@Composable
fun DeleteDialog(
    setShowDeleteDialog: (Boolean) -> Unit,
    onDeleteClicked: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { setShowDeleteDialog(false) },
        title = {
            Text(
                text = stringResource(id = R.string.delete_title),
                color = Color.White
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.delete_warning),
                color = Color.White
            )
        },
        dismissButton = {
            TextButton(
                onClick = {
                    setShowDeleteDialog(false)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = Color.White
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDeleteClicked()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.delete_data),
                    color = Color.White
                )
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
            setShowDeleteDialog = {},
            onDeleteClicked = {}
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
            lingkarKepala = "",
            lingkarLengan = "",
            onLingkarKepalaChanged = {},
            onLingkarLenganChanged = {},
            showDeleteDialog = false,
            setShowDeleteDialog = {},
            onDeleteClicked = {},
            onUpdateClicked = {},
            loading = false
        )
    }
}