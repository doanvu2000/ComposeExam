package com.jin.composeexam.ui.test.chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.min

// Data class for category information
data class CategoryData(
    val name: String,
    val percentage: Float,
    val color: Color
)

// Sample data for the chart
val categoryDataList = listOf(
    CategoryData("Transaction", 0.55f, Color(0xFFF7879A)),
    CategoryData("Transfer", 0.15f, Color(0xFF77D0C7)),
    CategoryData("Travel", 0.10f, Color(0xFFB593F9)),
    CategoryData("Food", 0.08f, Color(0xFFF8A3D1)),
    CategoryData("Shopping", 0.07f, Color(0xFFF9B883)),
    CategoryData("Car", 0.05f, Color(0xFF88D8C3))
)

// Composable function to draw the category chart
@Composable
fun CategoryChart(
    modifier: Modifier = Modifier,
    dataList: List<CategoryData>,
    gapSize: Dp = 10.dp
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val centerX = canvasWidth / 2f
            val centerY = canvasHeight / 2f
            val radius = min(centerX, centerY) * 0.8f
            val gapAngle = gapSize.toPx() * 360 / (2 * Math.PI * radius).toFloat()
            val totalGapAngle = dataList.size * gapAngle // Each gap for each item

            val totalPercentage = dataList.sumOf { it.percentage.toDouble() }
            val totalArcAngle = 360f - totalGapAngle
            var startAngle = -90f // Start from the top

            dataList.forEachIndexed { index, data ->
                val sweepAngle = (data.percentage / totalPercentage).toFloat() * totalArcAngle

                // Adjust start angle for first and last arcs
                val adjustedStartAngle = when (index) {
                    0 -> startAngle - gapAngle / 2 // Half gap before first arc
                    else -> startAngle
                }

                val adjustedSweepAngle = when (index) {
                    dataList.size - 1 -> sweepAngle + gapAngle / 2 // Half gap after last arc
                    else -> sweepAngle
                }

                val topLeft = Offset(centerX - radius, centerY - radius)
                val arcSize = Size(radius * 2, radius * 2)

                drawArc(
                    color = data.color,
                    startAngle = adjustedStartAngle,
                    sweepAngle = adjustedSweepAngle,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = 20.dp.toPx(), cap = StrokeCap.Round)
                )

                startAngle += sweepAngle + gapAngle
            }
        }

        val totalPercentage = dataList.sumOf { it.percentage.toDouble() } * 100
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${totalPercentage.toInt()}%",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Text(
                text = dataList[0].name,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

// Composable function for the legend
@Composable
fun LegendItem(categoryData: CategoryData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(categoryData.color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = categoryData.name,
            fontSize = 14.sp
        )
    }
}

// Main Composable for the chart and legend
@Composable
fun CategoryChartView(
    modifier: Modifier = Modifier,
    dataList: List<CategoryData>,
    gapSize: Dp = 10.dp
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Category Chart",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )

        CategoryChart(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            dataList = dataList,
            gapSize = gapSize
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Legend
        LazyVerticalGrid(
            state = rememberLazyGridState(), columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            items(dataList.size) { index ->
                LegendItem(categoryData = dataList[index])
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryChartViewPreview() {
    MaterialTheme {
        CategoryChartView(dataList = categoryDataList)
    }
}