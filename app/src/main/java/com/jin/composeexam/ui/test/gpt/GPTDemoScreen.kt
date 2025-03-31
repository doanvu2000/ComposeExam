package com.jin.composeexam.ui.test.gpt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jin.composeexam.ui.BaseButtonNext
import com.jin.composeexam.ui.ColumnCenterItem

/**
 * Using AnimatedSemiCircleProgress render by ChatGPT
 * */
@Composable
fun GPTDemoScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        BaseButtonNext({ navController.popBackStack() }, "Back")
        ColumnCenterItem(Modifier.fillMaxSize()) {
            ProgressDemo()
        }
    }
}