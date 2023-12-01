package com.littlegrow.capstone_project.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.components.BMIContainer
import com.littlegrow.capstone_project.ui.components.FeatureRow
import com.littlegrow.capstone_project.ui.components.InformationRow
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Scaffold {
        ConstraintLayout(
            modifier = modifier
                .padding(it)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            val (containerRes, featureRef, featureRowRef, informationRef, informationRowRef) = createRefs()
            BMIContainer(
                background = R.color.light_blue,
                bmiText = "Under Weight",
                bmiIndex = 15.2,
                modifier = Modifier.constrainAs(containerRes) {
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    top.linkTo(parent.top, 32.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = stringResource(id = R.string.features),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .constrainAs(featureRef) {
                        top.linkTo(containerRes.bottom, 40.dp)
                        start.linkTo(parent.start, 16.dp)
                    }
            )

            FeatureRow(
                featureList = listOf(
                    "Recommendation",
                    "?",
                    "?"
                ),
                modifier = Modifier
                    .constrainAs(featureRowRef) {
                        top.linkTo(featureRef.bottom, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                        width = Dimension.fillToConstraints
                    }
            )

            Text(
                text = stringResource(id = R.string.information),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .constrainAs(informationRef) {
                        top.linkTo(featureRowRef.bottom, 48.dp)
                        start.linkTo(parent.start, 16.dp)
                    }
            )

            InformationRow(
                articleList = listOf(
                    "Pencegahan Stunting Pada Anak",
                    "4 Cara Pencegahan Stunting"
                ),
                modifier = Modifier
                    .constrainAs(informationRowRef) {
                        start.linkTo(parent.start)
                        top.linkTo(informationRef.bottom)
                    }
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    device = Devices.DEFAULT,
    showSystemUi = true
)
fun HomeScreenPreview() {
    Capstone_ProjectTheme {
        HomeScreen()
    }
}