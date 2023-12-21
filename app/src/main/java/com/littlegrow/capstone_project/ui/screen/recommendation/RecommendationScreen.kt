package com.littlegrow.capstone_project.ui.screen.recommendation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.di.Injection
import com.littlegrow.capstone_project.model.FoodPredictData
import com.littlegrow.capstone_project.model.PredictionsItem
import com.littlegrow.capstone_project.ui.components.item.ProfileItem
import com.littlegrow.capstone_project.ui.components.item.RecommendationItem
import com.littlegrow.capstone_project.ui.screen.Result
import com.littlegrow.capstone_project.ui.screen.ViewModelFactory
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun RecommendationScreen(
    profileId: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: RecommendationViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepo(context))
    )
) {
    var listFood: List<PredictionsItem> = remember { mutableStateListOf() }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var bmiResult by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }

    viewModel.profileDetail.collectAsState().value.let { result ->
        when (result) {
            is Result.Loading -> {
                viewModel.getProfileData(profileId)
            }
            is Result.Success -> {
                LaunchedEffect(key1 = Unit) {
                    gender = if (result.data.jenisKelamin == "Laki") {
                        context.getString(R.string.male)
                    } else {
                        context.getString(R.string.female)
                    }

                    name = result.data.namaAnak
                    weight = result.data.beratBadan.toString()
                    height = result.data.tinggiBadan.toString()
                    bmiResult = result.data.statusUser

                    val foodPredictData = FoodPredictData(
                        weight = result.data.beratBadan,
                        height = result.data.tinggiBadan,
                        head_circumference = result.data.lingkarKepala,
                        arm_circumference = result.data.lingkarLengan,
                        history_of_illness = result.data.riwayatPenyakit.trim() != "-",
                        birth_spacing = result.data.jarakKelahiran
                    )
                    viewModel.getFoodList(foodPredictData)
                }
            }

            is Result.Error -> {
                Log.d("ProfileData: ", result.error)
                Toast.makeText(context, "Profile: ${result.error}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    viewModel.foodList.collectAsState().value.let {result ->
        when(result) {
            is Result.Loading -> loading = true
            is Result.Success -> {
                loading = false
                listFood = result.data
            }
            is Result.Error -> {
                loading = false
                Log.d("FoodList: ", result.error)
                Toast.makeText(context, "FoodList: ${result.error}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    RecommendationContent(
        onBackClick = onBackClick,
        name = name,
        gender = gender,
        weight = weight,
        height = height,
        bmiResult = bmiResult,
        foodList = listFood,
        loading = loading,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationContent(
    name: String,
    gender: String,
    weight: String,
    height: String,
    bmiResult: String,
    loading: Boolean,
    foodList: List<PredictionsItem>,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.recommendation),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = stringResource(id = R.string.back),
                            tint = Color.White,
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Spacer(modifier.padding(16.dp))
            ProfileItem(
                name = name,
                gender = gender,
                weight = stringResource(id = R.string.weight_display, weight),
                height = stringResource(id = R.string.height_display, height),
                bmiResult = bmiResult,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            RecommendationItem(
                foodList = foodList,
                loading = loading,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RecommendationScreenPreview() {
    Capstone_ProjectTheme {
        RecommendationContent(
            onBackClick = {},
            name = "Normal",
            weight = "",
            height = "",
            bmiResult = "",
            gender = "",
            foodList = listOf(),
            loading = false
        )
    }
}