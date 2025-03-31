package com.jin.composeexam.ui.test.gpt

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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

@Composable
fun AnimatedSemiCircleProgress(
    targetProgress: Float, // giá trị mục tiêu từ 0f đến 1f
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 20.dp,
    progressColor: Color = Color.Blue,
    backgroundColor: Color = Color.LightGray,
) {
    // Sử dụng animateFloatAsState để tạo animation khi targetProgress thay đổi
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )

    Canvas(modifier = modifier) {
        val radius = (size.minDimension / 2) - strokeWidth.toPx()
        // Vẽ background arc (nền)
        drawArc(
            color = backgroundColor,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
            topLeft = Offset(
                x = (size.width / 2) - radius,
                y = size.height - 2 * radius
            ),
            size = Size(radius * 2, radius * 2)
        )
        // Vẽ progress arc với giá trị animatedProgress
        drawArc(
            color = progressColor,
            startAngle = 180f,
            sweepAngle = 180f * animatedProgress,
            useCenter = false,
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
            topLeft = Offset(
                x = (size.width / 2) - radius,
                y = size.height - 2 * radius
            ),
            size = Size(radius * 2, radius * 2)
        )
    }
}
