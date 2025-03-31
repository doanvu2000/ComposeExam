package com.jin.composeexam.ui.test.gpt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgressDemo() {
    var progress by remember { mutableStateOf(0.5f) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedSemiCircleProgress(
            targetProgress = progress,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Slider(
            value = progress,
            onValueChange = { progress = it },
            valueRange = 0f..1f,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}
