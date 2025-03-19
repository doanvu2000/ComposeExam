package com.jin.composeexam.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jin.composeexam.data.model.Screen.Home
import com.jin.composeexam.data.model.Screen.Info
import com.jin.composeexam.data.model.Screen.Login
import com.jin.composeexam.data.model.Screen.Province
import com.jin.composeexam.ui.home.HomeScreen
import com.jin.composeexam.ui.info.InfoScreen
import com.jin.composeexam.ui.login.LoginScreen
import com.jin.composeexam.ui.province.ProvinceScreen
import com.jin.composeexam.ui.province.ProvinceViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val startDestination = Login.route
    val provinceViewModel: ProvinceViewModel = viewModel<ProvinceViewModel>()

    NavHost(navController = navController, startDestination = startDestination) {
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