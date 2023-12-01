package com.littlegrow.capstone_project.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.littlegrow.capstone_project.ui.theme.Capstone_ProjectTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Scaffold {
        Column(
            modifier = modifier
                .padding(it)
        ) {

        }
    }
}

@Composable
fun BMIContainer(
    modifier: Modifier = Modifier
) {

}

@Composable
@Preview(
    showBackground = true,
    device = Devices.DEFAULT,
    showSystemUi = true
)
fun HomeScreenPreview(){
    Capstone_ProjectTheme {
        HomeScreen()
    }
}