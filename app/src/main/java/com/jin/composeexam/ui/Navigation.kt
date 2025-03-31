package com.jin.composeexam.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jin.composeexam.data.model.Screen
import com.jin.composeexam.data.model.Screen.Home
import com.jin.composeexam.data.model.Screen.Info
import com.jin.composeexam.data.model.Screen.Login
import com.jin.composeexam.data.model.Screen.Province
import com.jin.composeexam.ui.home.HomeScreen
import com.jin.composeexam.ui.info.InfoScreen
import com.jin.composeexam.ui.login.LoginScreen
import com.jin.composeexam.ui.province.ProvinceScreen
import com.jin.composeexam.ui.province.ProvinceViewModel
import com.jin.composeexam.ui.test.gemini.GeminiDemoScreen
import com.jin.composeexam.ui.test.gpt.GPTDemoScreen
import com.jin.composeexam.ui.test.start.StartScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val startDestination = Screen.Start.route //test curved progress by AI
    val provinceViewModel: ProvinceViewModel = viewModel<ProvinceViewModel>()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Start.route) {
            StartScreen(navController)
        }
        composable(Screen.GeminiDemo.route) {
            GeminiDemoScreen(navController)
        }
        composable(Screen.GPTDemo.route) {
            GPTDemoScreen(navController)
        }
        composable(Login.route) {
            LoginScreen(navController)
        }
        composable(Info.route) {
            InfoScreen(navController)
        }
        composable(Province.route) {
            ProvinceScreen(navController, provinceViewModel)
        }
        composable(Home.route) {
            HomeScreen(navController)
        }
    }
}