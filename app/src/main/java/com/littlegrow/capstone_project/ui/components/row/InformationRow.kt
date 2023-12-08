package com.littlegrow.capstone_project.ui.components.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.littlegrow.capstone_project.data.local.database.Information
import com.littlegrow.capstone_project.ui.components.item.InformationItem
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun InformationRow(
    articleList: List<Information>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(articleList) {article ->
            InformationItem(
                articleImage = article.articleImage,
                articleTitle = article.articleTitle,
                articleLink = article.articleLink,
                articleSource = article.articleSource
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
fun InformationRowPreview() {
    Capstone_ProjectTheme {
        InformationRow(
            articleList = listOf()
        )
    }
}