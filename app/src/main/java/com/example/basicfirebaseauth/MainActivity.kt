package com.example.basicfirebaseauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.basicfirebaseauth.ui.screens.auth.AuthViewModel
import com.example.basicfirebaseauth.ui.theme.BasicFirebaseAuthTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel: AuthViewModel by viewModels<AuthViewModel>()
        setContent {
            BasicFirebaseAuthTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
                    DefaultAppEntryPoint(modifier = Modifier.padding(innerPadding), authViewModel)
                }
            }
        }
    }
}
