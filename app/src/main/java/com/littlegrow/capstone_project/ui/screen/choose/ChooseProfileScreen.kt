package com.littlegrow.capstone_project.ui.screen.choose

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.di.Injection
import com.littlegrow.capstone_project.model.DetailDataResponse
import com.littlegrow.capstone_project.ui.components.item.ProfileRowItem
import com.littlegrow.capstone_project.ui.screen.Result
import com.littlegrow.capstone_project.ui.screen.ViewModelFactory
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun ChooseProfileScreen(
    featureId: String,
    navigateToRecommendation: (String) -> Unit,
    navigateToBudget: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: ChooseViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepo(context))
    )
) {
    var listProfile: List<DetailDataResponse> = remember { mutableStateListOf() }
    var profileId by remember { mutableStateOf("") }

    viewModel.profileList.collectAsState().value.let { result ->
        when (result) {
            is Result.Loading -> viewModel.getAllProfiles()
            is Result.Success -> {
                listProfile = result.data
            }

            is Result.Error -> {
                Log.d("ProfileList: ", result.error)
                Toast.makeText(context, "ProfileList: ${result.error}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    ChooseProfileContent(
        setProfileId = { profileId = it },
        listProfile = listProfile,
        navigateToFeature = {
            if (featureId == "F1") {
                navigateToRecommendation(profileId)
            } else if (featureId == "F2") {
                navigateToBudget()
            }
        },
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseProfileContent(
    setProfileId: (String) -> Unit,
    navigateToFeature: () -> Unit,
    listProfile: List<DetailDataResponse>,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.choose_screen),
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
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(innerPadding)
        ) {
            items(listProfile) { profile ->
                val listDesc = listOf(
                    stringResource(id = R.string.weight_display, profile.beratBadan),
                    stringResource(id = R.string.height_display, profile.tinggiBadan),
                )
                ProfileRowItem(
                    name = profile.namaAnak,
                    listDescription = listDesc,
                    modifier = Modifier
                        .clickable {
                            setProfileId(profile.profileId)
                            navigateToFeature()
                        }
                )
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    device = Devices.DEFAULT,
    showSystemUi = true
)
fun ChooseProfileScreenPreview() {
    Capstone_ProjectTheme {
        ChooseProfileScreen(
            featureId = "",
            onBackClick = {},
            navigateToRecommendation = {},
            navigateToBudget = {},
        )
    }
}