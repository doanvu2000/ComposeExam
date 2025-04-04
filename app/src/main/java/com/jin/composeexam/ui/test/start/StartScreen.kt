package com.jin.composeexam.ui.test.start

import MonthlyGraphView
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jin.composeexam.data.model.Screen
import com.jin.composeexam.ui.BaseButtonNext
import com.jin.composeexam.ui.ColumnCenterItem
import com.jin.composeexam.ui.theme.backgroundColor2
import com.jin.composeexam.ui.theme.backgroundColor3
import com.jin.composeexam.ui.theme.backgroundColor4
import com.jin.composeexam.ui.theme.primaryColor3
import graphDataPoints_Long
import monthLabels_Long

@Composable
fun StartScreen(navController: NavController) {
    ColumnCenterItem(Modifier.fillMaxSize()) {
        MonthlyGraphView(
            data = graphDataPoints_Long,
            labels = monthLabels_Long
        )

        Spacer(Modifier.height(20.dp))

        BaseButtonNext(
            {
                navController.navigate(Screen.GeminiDemo.route)
            }, "Gemini Demo", backgroundColor3,
            textColor = primaryColor3
        )

        BaseButtonNext(
            {
                navController.navigate(Screen.GPTDemo.route)
            }, "GPT Demo",
            backgroundColor = backgroundColor2,
            textColor = backgroundColor4
        )
    }
}