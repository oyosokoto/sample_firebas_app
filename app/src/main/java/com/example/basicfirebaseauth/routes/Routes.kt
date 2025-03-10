package com.example.basicfirebaseauth.routes

sealed class Screen(val route: String) {
    object HOME : Screen("home")
    object LOGIN : Screen("login")
    object SIGNUP : Screen("register")

    companion object {
        fun getStartDestination() = LOGIN.route
    }
}