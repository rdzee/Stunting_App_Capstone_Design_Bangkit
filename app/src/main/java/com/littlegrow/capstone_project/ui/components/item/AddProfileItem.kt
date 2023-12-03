package com.littlegrow.capstone_project.ui.components.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun AddProfileItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .height(210.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (addRef) = createRefs()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .constrainAs(addRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                )

                Text(
                    text = stringResource(id = R.string.add_new_data),
                )
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
fun AddProfileItemPreview() {
    Capstone_ProjectTheme {
        AddProfileItem(
            onClick = {}
        )
    }
}