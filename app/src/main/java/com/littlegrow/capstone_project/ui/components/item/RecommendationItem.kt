package com.littlegrow.capstone_project.ui.components.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.model.PredictionsItem
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun RecommendationItem(
    foodList: List<PredictionsItem>,
    loading: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .heightIn(min = 200.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = modifier.padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.recommendation),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = modifier.height(16.dp))
                LazyColumn {
                    items(foodList) { data ->
                        if (data.probability != 0.0) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = data.label
                                )
                                Text(
                                    text = "${ (String.format("%.2f", data.probability).toDouble() * 100).toInt() }%"
                                )
                            }
                        }
                    }
                }
            }

            if (loading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NutritionRecommendationItemPreview() {
    Capstone_ProjectTheme {
        RecommendationItem(
            foodList = listOf(),
            loading = true
        )
    }
}