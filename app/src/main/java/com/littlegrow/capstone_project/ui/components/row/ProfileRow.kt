package com.littlegrow.capstone_project.ui.components.row

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.littlegrow.capstone_project.ui.components.item.AddProfileItem
import com.littlegrow.capstone_project.ui.components.item.ProfileItem
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileRow(
    profileList: List<String>,
    navigateToDetail: () -> Unit,
    navigateToAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState (pageCount = {
        profileList.size + 1
    })
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        HorizontalPager(
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 16.dp,
            state = pagerState
        ) {page ->
            if (page == profileList.lastIndex + 1) {
                AddProfileItem(
                    onClick = navigateToAdd
                )
            } else {
                ProfileItem(
                    name = "John Doe",
                    age = "2 year(s) 11 month(s)",
                    gender = "Male",
                    weight = "10 Kg",
                    height = "80 Cm",
                    bmiResult = "Normal",
                    bmiIndex = "15.6",
                    onClick = navigateToDetail
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
fun ProfileRowPreview() {
    Capstone_ProjectTheme {
        ProfileRow(
            profileList = listOf(
                "1",
                "2"
            ),
            navigateToAdd = {},
            navigateToDetail = {}
        )
    }
}
