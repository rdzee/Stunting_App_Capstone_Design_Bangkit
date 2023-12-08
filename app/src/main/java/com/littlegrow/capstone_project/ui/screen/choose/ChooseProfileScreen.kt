package com.littlegrow.capstone_project.ui.screen.choose

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.components.item.ProfileRowItem
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun ChooseProfileScreen(
    featureId: String,
    navigateToRecommendation: () -> Unit,
    navigateToFeature2: () -> Unit,
    navigateToFeature3: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listProfile: List<String> = listOf(
        "1", "2"
    )

    ChooseProfileContent(
        listProfile = listProfile,
        navigateToFeature = if (featureId == "F1") navigateToRecommendation else if (featureId == "F2") navigateToFeature2 else navigateToFeature3,
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseProfileContent(
    navigateToFeature: () -> Unit,
    listProfile: List<String>,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.choose_screen),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
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
                ProfileRowItem(
                    name = "John Doe",
                    listDescription = listProfile,
                    navigateToFeature = navigateToFeature
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
            navigateToFeature2 = {},
            navigateToFeature3 = {}
        )
    }
}