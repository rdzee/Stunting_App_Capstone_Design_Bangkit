package com.littlegrow.capstone_project.ui.components.row

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.model.DetailDataResponse
import com.littlegrow.capstone_project.ui.components.item.AddProfileItem
import com.littlegrow.capstone_project.ui.components.item.ProfileItem
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileRow(
    profileList: List<DetailDataResponse>,
    navigateToDetail: (String) -> Unit,
    navigateToAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    var gender by remember { mutableStateOf("") }
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
                gender = if (profileList[page].jenisKelamin == "Laki") {
                    stringResource(id = R.string.male)
                } else {
                    stringResource(id = R.string.female)
                }
                ProfileItem(
                    name = profileList[page].namaAnak,
                    gender = gender,
                    weight = stringResource(id = R.string.weight_display, profileList[page].beratBadan),
                    height = stringResource(id = R.string.height_display, profileList[page].tinggiBadan),
                    bmiResult = profileList[page].statusUser,
                    modifier = Modifier
                        .clickable { navigateToDetail(profileList[page].profileId) }
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
            profileList = listOf(),
            navigateToAdd = {},
            navigateToDetail = {}
        )
    }
}
