package com.littlegrow.capstone_project.navigation

sealed class Screen(val route: String) {
    data object Login: Screen("login")
    data object Home: Screen("home")

    data object Detail: Screen("detail")

    data object Add: Screen("add")

    data object Recommendation: Screen("recommendation")

    data object ChooseProfile: Screen("chooseProfile/{featureId}") {
        fun createRoute(featureId: String) = "chooseProfile/$featureId"
    }

}