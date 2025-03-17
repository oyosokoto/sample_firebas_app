package com.example.basicfirebaseauth.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.basicfirebaseauth.routes.Screen
import com.example.basicfirebaseauth.ui.theme.Error
import com.example.basicfirebaseauth.ui.theme.Purple40

@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authState = authViewModel.authState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(
                context, (authState.value as AuthState.Error).message,
                Toast.LENGTH_LONG
            ).show()

            AuthState.LOADING -> {
                Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
            }

            AuthState.UNAuthenticated -> {}
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Login", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    emailError.ifBlank { "Email Address" },
                    color = if (emailError.isNotEmpty()) Error else Purple40
                )
            },
            leadingIcon = {
                Icon(Icons.Rounded.AccountCircle, contentDescription = "")
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
            ,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Transparent,
                unfocusedTextColor = Transparent
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(
                    emailError.ifBlank { "Password" },
                    color = if (passwordError.isNotEmpty()) Error else Purple40
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Rounded.Lock, contentDescription = ""
                )
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(
                    if (passwordVisible)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff,
                    contentDescription = "Toggle Visibility",
                    modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            authViewModel.login(email, password)
        }) {
            Text("Continue")
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            navController.navigate(Screen.SIGNUP.route)
        }) {
            Text("Don't have an account ? Register!")
        }
    }


}