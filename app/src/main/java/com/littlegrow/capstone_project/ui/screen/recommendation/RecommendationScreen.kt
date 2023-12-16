package com.littlegrow.capstone_project.ui.screen.recommendation

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.components.item.ProfileItem
import com.littlegrow.capstone_project.ui.components.item.RecommendationItem
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.recommendation),
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
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ) {
            ProfileItem(
                name = stringResource(id = R.string.name),
                gender = stringResource(id = R.string.gender),
                weight = stringResource(id = R.string.weight),
                height = stringResource(id = R.string.height),
                bmiResult = stringResource(id = R.string.bmi),
                bmiIndex = stringResource(id = R.string.bmi),
                navigateToDetail = { },
                navigateToAdd = { },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            RecommendationItem(
                calorie = stringResource(id = R.string.calorie),
                protein = stringResource(id = R.string.protein),
                vitamin = stringResource(id = R.string.vitamin),
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
        RecommendationScreen(
            onBackClick = {}
        )
    }
}