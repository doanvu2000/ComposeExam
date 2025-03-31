package com.jin.composeexam.ui.test.start

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jin.composeexam.data.model.Screen
import com.jin.composeexam.ui.BaseButtonNext
import com.jin.composeexam.ui.ColumnCenterItem
import com.jin.composeexam.ui.theme.backgroundColor2
import com.jin.composeexam.ui.theme.backgroundColor3
import com.jin.composeexam.ui.theme.backgroundColor4
import com.jin.composeexam.ui.theme.primaryColor3

@Composable
fun StartScreen(navController: NavController) {
    ColumnCenterItem(Modifier.fillMaxSize()) {
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