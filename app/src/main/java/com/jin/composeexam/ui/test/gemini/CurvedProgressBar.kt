package com.jin.composeexam.ui.test.gemini

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Composable để vẽ một Progress Bar cong 180 độ (hình bán nguyệt).
 *
 * @param progress Giá trị tiến trình hiện tại (từ 0.0f đến 1.0f).
 * @param modifier Modifier để tùy chỉnh layout, kích thước, v.v.
 * @param strokeWidth Độ dày của đường vẽ progress.
 * @param foregroundColor Màu của phần tiến trình đã hoàn thành.
 * @param backgroundColor Màu của phần nền (phần chưa hoàn thành).
 * @param animationDurationMillis Thời gian (ms) cho hiệu ứng animation khi progress thay đổi.
 * @param animationDelayMillis Thời gian (ms) trì hoãn trước khi bắt đầu animation.
 */
@Composable
fun CurvedProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 8.dp,
    foregroundColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    animationDurationMillis: Int = 1000, // 1 giây
    animationDelayMillis: Int = 0
) {
    // Đảm bảo progress nằm trong khoảng [0, 1]
    val clampedProgress = progress.coerceIn(0f, 1f)

    // Sử dụng animateFloatAsState để tạo hiệu ứng mượt mà khi progress thay đổi
    val animatedProgress by animateFloatAsState(
        targetValue = clampedProgress,
        animationSpec = tween(
            durationMillis = animationDurationMillis,
            delayMillis = animationDelayMillis
        ),
        label = "ProgressAnimation" // Nhãn giúp debug animation
    )

    Canvas(modifier = modifier) {
        val diameter = size.minDimension // Đường kính dựa vào chiều nhỏ nhất của Canvas
        val strokePx = strokeWidth.toPx()
        val radius = (diameter - strokePx) / 2f
        val topLeftOffset = Offset(strokePx / 2, strokePx / 2)
        val arcSize = Size(diameter - strokePx, diameter - strokePx)

        // Góc bắt đầu (180 độ - phía bên trái)
        val startAngle = 180f
        // Góc quét tối đa (180 độ - tạo thành hình bán nguyệt)
        val maxSweepAngle = 180f
        // Góc quét dựa trên tiến trình đã được animation
        val progressSweepAngle = animatedProgress * maxSweepAngle

        // 1. Vẽ phần nền (background) của progress bar (luôn là 180 độ)
        drawArc(
            color = backgroundColor,
            startAngle = startAngle,
            sweepAngle = maxSweepAngle, // Luôn vẽ đủ 180 độ cho nền
            useCenter = false, // Chỉ vẽ đường viền, không vẽ hình quạt
            topLeft = topLeftOffset,
            size = arcSize,
            style = Stroke(width = strokePx, cap = StrokeCap.Round) // Bo tròn đầu mút
        )

        // 2. Vẽ phần tiến trình (foreground) dựa trên animatedProgress
        drawArc(
            color = foregroundColor,
            startAngle = startAngle,
            sweepAngle = progressSweepAngle, // Góc quét thay đổi theo tiến trình
            useCenter = false,
            topLeft = topLeftOffset,
            size = arcSize,
            style = Stroke(width = strokePx, cap = StrokeCap.Round) // Bo tròn đầu mút
        )
    }
}