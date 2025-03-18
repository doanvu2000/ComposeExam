package com.jin.composeexam.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jin.composeexam.ui.Screen.Home
import com.jin.composeexam.ui.Screen.Info
import com.jin.composeexam.ui.Screen.Login
import com.jin.composeexam.ui.home.HomeScreen
import com.jin.composeexam.ui.info.InfoScreen
import com.jin.composeexam.ui.login.LoginScreen
import com.jin.composeexam.ui.state.ProvinceScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login.route) {
        composable(Login.route) {
            LoginScreen(navController)
        }
        composable(Info.route) {
            InfoScreen(navController)
        }
        composable(Screen.Province.route) {
            ProvinceScreen(navController)
        }
        composable(Home.route) {
            HomeScreen(navController)
        }
    }
}