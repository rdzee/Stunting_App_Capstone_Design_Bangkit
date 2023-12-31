package com.littlegrow.capstone_project.ui.components.item

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun InformationItem(
    articleImage: String,
    articleTitle: String,
    articleLink: String,
    articleSource: String,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(articleLink))
    Column(
        modifier = modifier
            .width(275.dp)
            .clickable {
                context.startActivity(intent)
            }
    ) {
        AsyncImage(
            model = articleImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(26f / 15f)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray)
        )
        Text(
            text = articleTitle,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )
        Text(
            text = stringResource(id = R.string.information_source, articleSource),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 4.dp)
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
            articleTitle = "Pencegahan Stunting Pada Anak",
            articleLink = "",
            articleSource = ""
        )
    }
}