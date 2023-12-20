package com.littlegrow.capstone_project.navigation

sealed class Screen(val route: String) {
    data object Login: Screen("login")
    data object Home: Screen("home")

    data object Detail: Screen("home/{profileId}") {
        fun createRoute(profileId: String) = "home/$profileId"
    }

    data object Add: Screen("add")

    data object Recommendation: Screen("home/feature/recommendation/{profileId}") {
        fun createRoute(profileId: String) = "home/feature/recommendation/$profileId"
    }

    data object Budget: Screen("home/feature/budget")

    data object ChooseProfile: Screen("home/feature/{featureId}") {
        fun createRoute(featureId: String) = "home/feature/$featureId"
    }

}