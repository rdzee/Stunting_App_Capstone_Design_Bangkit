package com.littlegrow.capstone_project.ui.components.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun RecommendationItem(
    calorie: String,
    protein: String,
    vitamin: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .height(210.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier.padding(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.recommendation),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.calorie),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = calorie,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.protein),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = protein,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.vitamin),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = vitamin,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NutritionRecommendationItemPreview() {
    Capstone_ProjectTheme {
        RecommendationItem(
            "Milna Bubur Bayi (170 kalori per 40 gram)",
            "Kacang Hijau (23 gram protein per 100 gram)",
            "Apel (Vitamin A dan C) dan Pisang (Vitamin B6)"
        )
    }
}