package com.aay.compose.barChart.components

import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.aay.compose.barChart.model.BarParameters

internal fun DrawScope.drawBarGroups(
    barsParameters: List<BarParameters>,
    upperValue: Double,
    lowerValue: Double,
    spacingY: Dp,
    spacingX: Dp,
    xAxisData: List<String>,
    barWidth: Float,
    xRegionWidth: Float,
    xRegionWidthWithoutSpacing: Float,
    spaceBetweenBars: Float,
    maxWidth: Dp,
    height: Dp,
) {

    barsParameters.forEachIndexed { barIndex, bar ->

        bar.data.forEachIndexed { index, data ->
            val ratio = (((data.toFloat() - lowerValue) / (upperValue)) / 1.12.dp.toPx()).dp.toPx()
            val barLength = ratio * (height.toPx() - (height.toPx() / 2f))


            val xAxisLength = barIndex * (xRegionWidthWithoutSpacing / xAxisData.size)
            val lengthWithRatio = xAxisLength.dp + (index * xRegionWidth.dp)

            drawRect(
                brush = Brush.verticalGradient(listOf(bar.barColor, bar.barColor)),
                topLeft = Offset(
                    lengthWithRatio.coerceAtMost(maxWidth).toPx(),
                    (height.value - barLength) - 5.dp.toPx()
                ),
                size = Size(
                    width = barWidth,
                    height = barLength
                ),
            )
        }
    }
}