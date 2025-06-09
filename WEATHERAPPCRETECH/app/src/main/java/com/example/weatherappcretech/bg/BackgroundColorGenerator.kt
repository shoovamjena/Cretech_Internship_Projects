package com.example.weatherappcretech.bg

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import kotlin.random.Random

object BackgroundColorGenerator {
    fun generate(): CurveShape {
        val colors = listOf(
            Color(0xFFE57373),
            Color(0xFF81C784),
            Color(0xFF64B5F6),
            Color(0xFFFFD54F)
        ).shuffled()

        val start = Offset(Random.nextFloat() * 1000, Random.nextFloat() * 2000)
        val cp1 = Offset(Random.nextFloat() * 5000, Random.nextFloat() * 5000)
        val cp2 = Offset(Random.nextFloat() * 5000, Random.nextFloat() * 5000)
        val end = Offset(Random.nextFloat() * 1000, Random.nextFloat() * 3000)

        val path = Path().apply {
            moveTo(start.x, start.y)
            cubicTo(cp1.x, cp1.y, cp2.x, cp2.y, end.x, end.y)
            close()
        }

        val gradient = Brush.linearGradient(
            colors = colors,
            start = Offset.Zero,
            end = Offset(7000f, 7000f)
        )

        return CurveShape(path, gradient)
    }

    data class CurveShape(
        val path: Path,
        val brush: Brush)
}