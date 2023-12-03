package com.littlegrow.capstone_project.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.components.item.InputHeaderItem
import com.littlegrow.capstone_project.ui.components.item.ProfileItem
import com.littlegrow.capstone_project.ui.components.item.RecommendationItem
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun RecommendationScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        InputHeaderItem(
            title = stringResource(id = R.string.recommendation),
            onBackClick = onBackClick
        )
        ProfileItem(
            name = stringResource(id = R.string.name),
            age = stringResource(id = R.string.age),
            gender = stringResource(id = R.string.gender),
            weight = stringResource(id = R.string.weight),
            height = stringResource(id = R.string.height),
            bmiResult = stringResource(id = R.string.bmi),
            bmiIndex = stringResource(id = R.string.bmi),
            onClick = {}
        )
        Spacer(modifier = Modifier.padding(16.dp))
        RecommendationItem(
            calorie = stringResource(id = R.string.calorie),
            protein = stringResource(id = R.string.protein),
            vitamin = stringResource(id = R.string.vitamin)
        )
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