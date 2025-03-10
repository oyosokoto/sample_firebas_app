package com.example.basicfirebaseauth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.basicfirebaseauth.routes.Screen
import com.example.basicfirebaseauth.ui.screens.auth.AuthViewModel
import com.example.basicfirebaseauth.ui.screens.home.HomePage
import com.example.basicfirebaseauth.ui.screens.auth.LoginPage
import com.example.basicfirebaseauth.ui.screens.auth.SignUpPage

@Composable
fun DefaultAppEntryPoint(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.getStartDestination(),
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.HOME.route) {
            HomePage(modifier, navController, viewModel)
        }

        composable(Screen.LOGIN.route) {
            LoginPage(modifier, navController, viewModel)
        }

        composable(Screen.SIGNUP.route) {
            SignUpPage(modifier, navController, viewModel)
        }
    }
}