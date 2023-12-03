package com.littlegrow.capstone_project.ui.components.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun InformationItem(
    articleImage: String,
    articleTitle: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .width(275.dp)
    ) {
        AsyncImage(
            model = articleImage,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(26f/15f)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray)
        )
        Text(
            text = articleTitle,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
fun InformationItemPreview(){
    Capstone_ProjectTheme {
        InformationItem(
            articleImage = "",
            articleTitle = "Pencegahan Stunting Pada Anak"
        )
    }
}