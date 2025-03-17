package com.example.basicfirebaseauth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.basicfirebaseauth.routes.Screen
import com.example.basicfirebaseauth.ui.screens.auth.AuthState
import com.example.basicfirebaseauth.ui.screens.auth.AuthViewModel
import com.example.basicfirebaseauth.ui.screens.dashboard.home.HomePage
import com.example.basicfirebaseauth.ui.screens.auth.LoginPage
import com.example.basicfirebaseauth.ui.screens.auth.SignUpPage

@Composable
fun DefaultAppEntryPoint(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    navController: NavHostController = rememberNavController()
) {
    val authState = viewModel.authState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (authState.value is AuthState.Authenticated) "dashboard" else "auth"
    ) {
        navigation(startDestination = Screen.LOGIN.route, route = "auth") {
            composable(Screen.LOGIN.route) {
                LoginPage(modifier, navController, viewModel)
            }

            composable(Screen.SIGNUP.route) {
                SignUpPage(modifier, navController, viewModel)
            }
        }

        navigation(startDestination = Screen.HOME.route, route = "dashboard") {
            composable(Screen.HOME.route) {
                HomePage(modifier, navController, viewModel)
            }
        }


    }
}