package com.jin.composeexam.ui.test.gemini

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jin.composeexam.ui.BaseButtonNext
import com.jin.composeexam.ui.ColumnCenterItem

/**
 * Using CurvedProgressBar composable render by Gemini
 * */
@Composable
fun GeminiDemoScreen(navController: NavController) {
    var currentProgress by remember { mutableFloatStateOf(0.3f) }

    Column(Modifier.padding(16.dp)) {
        BaseButtonNext({ navController.popBackStack() }, "Back")
        ColumnCenterItem(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                "Tiến trình: ${(currentProgress * 100).toInt()}%",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(32.dp))

            CurvedProgressBar(
                progress = currentProgress,
                modifier = Modifier.size(250.dp), // Kích thước của Canvas chứa progress bar
                strokeWidth = 20.dp,
                foregroundColor = Color(0xFF4CAF50), // Màu xanh lá
                backgroundColor = Color.LightGray
            )

            Spacer(modifier = Modifier.height(32.dp))

            Slider(
                value = currentProgress,
                onValueChange = { currentProgress = it },
                valueRange = 0f..1f // Slider điều khiển giá trị từ 0 đến 1
            )

            Spacer(modifier = Modifier.height(16.dp))

            BaseButtonNext(
                onClick = { currentProgress = (0..100).random() / 100f },
                "Random Progress"
            )
        }
    }
}