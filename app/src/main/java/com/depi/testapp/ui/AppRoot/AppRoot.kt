package com.depi.testapp.ui.AppRoot

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.depi.testapp.constraint.enProfile
import com.depi.testapp.constraint.routes
import com.depi.testapp.ui.features.auth.LoginScreen
import com.depi.testapp.ui.features.auth.RegisterScreen
import com.depi.testapp.ui.features.auth.WelcomeScreen
import com.depi.testapp.ui.features.main_screen.MainScreen
import com.depi.testapp.ui.features.splash_screen.SplashScreen

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = routes.welcomeScreen,
        enterTransition = { slideInHorizontally(initialOffsetX = { 700 }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -700 }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -700 }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 700 }) + fadeOut() },
        builder = {
            composable(routes.welcomeScreen) {
                WelcomeScreen(navController)
            }
            composable(routes.loginScreen) {
                LoginScreen(navController)
            }
            composable(routes.registerScreen) {
                RegisterScreen(navController)
            }
            composable("${routes.mainScreen}/{userType}") {
                backStackEntry ->
                val userType = backStackEntry.arguments?.getString("userType")?:"geust"
                val profileEnum = if(userType =="user") enProfile.user else enProfile.geust
                MainScreen(navController,profileEnum)
            }
            composable (routes.SplashScreen){
                SplashScreen(navController)
            }
        })
}