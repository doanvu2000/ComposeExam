package com.jin.composeexam.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jin.composeexam.util.Constants

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "${Constants.displayName} - " +
                    "${Constants.gender} - " +
                    "${Constants.result}"
        )
    }
}