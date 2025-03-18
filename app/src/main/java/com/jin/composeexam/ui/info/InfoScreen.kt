package com.jin.composeexam.ui.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun InfoScreen(navHostController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        Text("Info Screen")
    }
}