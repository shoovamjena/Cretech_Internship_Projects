package com.example.weatherappcretech.bg

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GradientBlurBackground(modifier: Modifier = Modifier) {
    val shapes = remember {
        List(5) {
            BackgroundColorGenerator.generate()
        }
    }
    val colors = listOf(
        Color(0xFFFFD1DC),
        Color(0xFFFFF3B0),
        Color(0xFFB5EAD7),
        Color(0xFFC7CEEA),
        Color(0xFFFFDAC1),
        Color(0xFFAEC6CF),
        Color(0xFFFDCBFC),
        Color(0xFFE0BBE4),
        Color(0xFFD5AAFF),
        Color(0xFFDEFDE0),
        Color(0xFFFFC9DE),
        Color(0xFFFBE8EB),
        Color(0xFFC1E1C1),
        Color(0xFFFFE0B2),
        Color(0xFFC8D8E4),
        Color(0xFFE6E6FA),
        Color(0xFFFFF0F5),
        Color(0xFFE0FFFF),
        Color(0xFFF0FFF0),
        Color(0xFFF5F5DC)
    ).random()


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors)
    ) {
        shapes.forEach { shape ->
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(30.dp) // Apply blur effect here
            ) {
                drawPath(
                    path = shape.path,
                    brush = shape.brush,
                    alpha = 1f
                )
            }
        }
    }
}
