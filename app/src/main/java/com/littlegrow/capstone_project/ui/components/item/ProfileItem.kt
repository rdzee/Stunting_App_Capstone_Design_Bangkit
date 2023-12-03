package com.littlegrow.capstone_project.ui.components.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun ProfileItem(
    name: String,
    age: String,
    gender: String,
    weight: String,
    height: String,
    bmiResult: String,
    bmiIndex: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .height(210.dp)
            .clickable {
                onClick()
            }
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (nameRef, ageRef, bmiResultRef, dataRowRef) = createRefs()
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(nameRef) {
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                        top.linkTo(parent.top, 16.dp)
                        width = Dimension.fillToConstraints
                    }

            )
            Text(
                text = age,
                fontSize = 12.sp,
                modifier = Modifier
                    .constrainAs(ageRef) {
                        top.linkTo(nameRef.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .constrainAs(dataRowRef) {
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                        top.linkTo(ageRef.bottom)
                        bottom.linkTo(bmiResultRef.top)
                    }
                    .fillMaxWidth()
            ) {
                DataColumn(
                    title = stringResource(id = R.string.gender),
                    data = gender
                )
                DataColumn(
                    title = stringResource(id = R.string.data_Weight),
                    data = weight
                )
                DataColumn(
                    title = stringResource(id = R.string.data_Height),
                    data = height
                )
                DataColumn(
                    title = stringResource(id = R.string.bmi),
                    data = bmiIndex
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.green),
                        shape = RoundedCornerShape(25.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .constrainAs(bmiResultRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, 16.dp)
                    }
            ) {
                Text(
                    text = bmiResult,
                    style = TextStyle(
                        color = Color.White,
                        shadow = Shadow(
                            Color.Black, Offset(2f, 1f)
                        ),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier
                )
            }


        }
    }
}

@Composable
fun DataColumn(
    title: String,
    data: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
        )

        Text(
            text = data,
            style = TextStyle(
                fontSize = 14.sp
            ),
            modifier = Modifier
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
fun ProfileContainerPreview() {
    Capstone_ProjectTheme {
        ProfileItem(
            name = "John Doe",
            age = "2 year(s) 11 month(s)",
            gender = "Male",
            weight = "10 Kg",
            height = "80 Cm",
            bmiResult = "Normal",
            bmiIndex = "15.6",
            onClick = {},
        )
    }
}