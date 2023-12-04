package com.littlegrow.capstone_project.ui.components.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun FeatureItem(
    featureId: String,
    featureName: String,
    navigateToChooseProfile: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .shadow(10.dp, shape = RoundedCornerShape(10.dp))
            .background(color = Color.LightGray)
            .size(100.dp)
            .padding(vertical = 8.dp)
            .clickable {
                navigateToChooseProfile(featureId)
            }
    ) {
        Text(
            text = featureName,
            textAlign = TextAlign.Center,
            fontSize = 10.sp,
            color = Color.DarkGray,
            modifier = Modifier
        )
    }
}

@Composable
@Preview(
    showBackground = false
)
fun FeatureItemPreview() {
    Capstone_ProjectTheme {
        FeatureItem(
            featureId = "",
            navigateToChooseProfile = {},
            featureName = stringResource(id = R.string.feature_recommendation)
        )
    }
}