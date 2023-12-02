package com.littlegrow.capstone_project

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.littlegrow.capstone_project.navigation.Screen
import com.littlegrow.capstone_project.ui.screen.HomeScreen
import com.littlegrow.capstone_project.ui.screen.LoginScreen

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
                HomeScreen()
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
        }
    }
}