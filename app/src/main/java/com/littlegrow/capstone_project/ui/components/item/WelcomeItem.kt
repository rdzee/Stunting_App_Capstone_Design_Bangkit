package com.littlegrow.capstone_project.ui.components.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.littlegrow.capstone_project.R
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun WelcomeItem(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.welcome_1),
            fontSize = 36.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier.padding(top = 36.dp)
        )
        Text(
            text = stringResource(id = R.string.welcome_2),
            fontSize = 36.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
        )
        Image(
            painter = painterResource(id = R.drawable.login_image_main),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
            modifier = modifier.padding(top = 36.dp)
                .width(300.dp)
        )
        Text(
            text = stringResource(id = R.string.little_grow),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.surface,
            modifier = modifier.padding(top = 36.dp)
        )
        Text(
            text = stringResource(id = R.string.little_grow_1),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.surface,
            modifier = modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(id = R.string.little_grow_2),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.surface,
            modifier = modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
fun WelcomeItemPreview() {
    Capstone_ProjectTheme {
        WelcomeItem()
    }
}