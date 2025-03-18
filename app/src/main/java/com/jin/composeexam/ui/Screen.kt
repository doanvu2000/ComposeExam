package com.jin.composeexam.ui

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Info : Screen("info")
    object Province : Screen("province")
    object Home : Screen("home")
}