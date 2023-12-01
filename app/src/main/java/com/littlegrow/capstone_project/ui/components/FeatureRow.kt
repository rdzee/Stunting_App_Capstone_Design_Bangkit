package com.littlegrow.capstone_project.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun FeatureRow(
    featureList: List<String>,
    modifier: Modifier = Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        featureList.forEach { feature->
            FeatureItem(
                featureName = feature
            )
        }
    }
}

@Composable
@Preview(
    showBackground = false
)
fun FeatureRowPreview(){
    Capstone_ProjectTheme {
        FeatureRow(
            featureList = listOf(
                "Recommendation",
                "Mystery",
                "Mystery"
            )
        )
    }
}