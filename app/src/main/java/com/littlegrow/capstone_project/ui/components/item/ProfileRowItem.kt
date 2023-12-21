package com.littlegrow.capstone_project.ui.components.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun ProfileRowItem(
    name: String,
    listDescription: List<String>,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (nameRef, descriptionRowRef, iconRef) = createRefs()

            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .constrainAs(nameRef) {
                        start.linkTo(parent.start, 16.dp)
                        top.linkTo(parent.top, 12.dp)
                    }
            )

            Row(
                modifier = Modifier
                    .constrainAs(descriptionRowRef) {
                        start.linkTo(parent.start, 16.dp)
                        bottom.linkTo(parent.bottom, 12.dp)
                    }
            ) {
                listDescription.mapIndexed { index, descItem ->
                    Text(
                        text = "${descItem}${if (index != listDescription.lastIndex) " | " else ""}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            Icon(
                imageVector = Icons.Outlined.ArrowForwardIos,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .constrainAs(iconRef) {
                        end.linkTo(parent.end, 16.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(16.dp)
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
fun ProfileRowItemPreview() {
    Capstone_ProjectTheme {
        ProfileRowItem(
            name = "John Doe",
            listDescription = listOf(
                "Laki - Laki",
                "14.2 Kg",
                "80 Cm"
            ),
        )
    }
}