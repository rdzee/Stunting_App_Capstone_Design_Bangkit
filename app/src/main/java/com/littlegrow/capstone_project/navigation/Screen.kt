package com.littlegrow.capstone_project.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("home")
}