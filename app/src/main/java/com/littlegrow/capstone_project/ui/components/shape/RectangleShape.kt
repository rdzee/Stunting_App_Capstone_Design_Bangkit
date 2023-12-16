package com.littlegrow.capstone_project.ui.components.shape

import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

@Composable
fun RectangleShape(
    shape: Shape,
    color: Color,
    modifier: Modifier = Modifier
    ) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .clip(shape)
                .background(color)
        )
    }
}