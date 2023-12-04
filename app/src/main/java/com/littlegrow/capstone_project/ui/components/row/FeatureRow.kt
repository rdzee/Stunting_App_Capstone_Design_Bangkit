package com.littlegrow.capstone_project.ui.components.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.littlegrow.capstone_project.model.FeatureData
import com.littlegrow.capstone_project.ui.components.item.FeatureItem
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun FeatureRow(
    featureList: List<FeatureData>,
    navigateToChooseProfile: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        featureList.forEach { feature->
            FeatureItem(
                featureId = feature.id,
                featureName = feature.featureName,
                navigateToChooseProfile = navigateToChooseProfile
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
                FeatureData(
                    id= "",
                    featureName = "Recommendation"
                )
            ),
            navigateToChooseProfile = {}
        )
    }
}