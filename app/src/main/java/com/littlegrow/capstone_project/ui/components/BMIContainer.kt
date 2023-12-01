package com.littlegrow.capstone_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun BMIContainer(
    background: Int,
    bmiText: String,
    bmiIndex: Double,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(colorResource(id = background), shape = RoundedCornerShape(10.dp))
            .height(200.dp)
            .width(300.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (bmiTextRef, bmiRef, bmiIndexRef) = createRefs()

            Text(
                text = stringResource(id = R.string.bmi),
                style = TextStyle(
                    color = Color.White,
                    shadow = Shadow(
                        Color.Black, Offset(2f, 1f)
                    ),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .constrainAs(bmiRef) {
                        start.linkTo(parent.start, 16.dp)
                        top.linkTo(parent.top, 16.dp)
                    }
            )

            Text(
                text = bmiIndex.toString(),
                style = TextStyle(
                    color = Color.White,
                    shadow = Shadow(
                        Color.Black, Offset(2f, 1f)
                    )
                ),
                modifier = Modifier.constrainAs(bmiIndexRef) {
                    top.linkTo(bmiRef.bottom)
                    start.linkTo(bmiRef.start)
                    end.linkTo(bmiRef.end)
                }
            )

            Text(
                text = bmiText,
                style = TextStyle(
                    color = Color.White,
                    shadow = Shadow(
                        Color.Black, Offset(2f, 1f)
                    ),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.constrainAs(bmiTextRef) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )


        }
    }
}

@Composable
@Preview(
    showBackground = true
)
fun BMIContainerPreview() {
    Capstone_ProjectTheme {
        BMIContainer(
            R.color.yellow,
            "Under Weight",
            15.2
        )
    }
}