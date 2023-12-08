package com.littlegrow.capstone_project

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.littlegrow.capstone_project.navigation.Screen
import com.littlegrow.capstone_project.ui.screen.choose.ChooseProfileScreen
import com.littlegrow.capstone_project.ui.screen.detail.DetailScreen
import com.littlegrow.capstone_project.ui.screen.home.HomeScreen
import com.littlegrow.capstone_project.ui.screen.input.InputDataScreen
import com.littlegrow.capstone_project.ui.screen.login.LoginScreen
import com.littlegrow.capstone_project.ui.screen.recommendation.RecommendationScreen
import com.littlegrow.capstone_project.util.FEATURE_ID

@Composable
fun CapstoneApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val user = Firebase.auth.currentUser
    val startRoute = if (user != null) Screen.Home.route else Screen.Login.route
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startRoute,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToLogin = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    },
                    navigateToDetail = {
                        navController.navigate(Screen.Detail.route)
                    },
                    navigateToAdd = {
                        navController.navigate(Screen.Add.route)
                    },
                    navigateToChooseProfile = { featureId ->
                        navController.navigate(Screen.ChooseProfile.createRoute(featureId))
                    }
                )
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    navigateToHome = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Screen.Detail.route) {
                DetailScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Screen.Add.route) {
                InputDataScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Screen.ChooseProfile.route,
                arguments = listOf(navArgument(FEATURE_ID) {
                    type = NavType.StringType
                })
            ) {
                val id = it.arguments?.getString(FEATURE_ID) ?: ""
                ChooseProfileScreen(
                    featureId = id,
                    navigateToRecommendation = {
                        navController.navigate(Screen.Recommendation.route)
                    },
                    navigateToFeature2 = {},
                    navigateToFeature3 = {},
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Screen.Recommendation.route) {
                RecommendationScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

        }
    }
}