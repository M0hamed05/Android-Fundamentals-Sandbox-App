package com.depi.testapp.ui.features.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.depi.testapp.ui.components.BackButton
import com.depi.testapp.ui.components.HeadlineWord
import com.depi.testapp.ui.components.MainButton
import com.depi.testapp.ui.components.MainTextField
import com.depi.testapp.ui.components.SocialLoginDivider

@Composable
fun RegisterScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var Email by remember { mutableStateOf("") }
    var Password by remember { mutableStateOf("") }
    var ConfirmPassword by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White

    ) {paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BackButton(onClick = {navController.popBackStack()})
            HeadlineWord("Hello!")
            MainTextField(text = username, placeholder = "username", OnValueChanged = {username = it})
            MainTextField(text = Email, placeholder = "Email", OnValueChanged = {Email = it})
            MainTextField(text = Password, placeholder = "Password", OnValueChanged = {Password = it})
            MainTextField(text = ConfirmPassword, placeholder = "ConfirmPassword", OnValueChanged = {ConfirmPassword = it})
            MainButton("Register", onClick = {})
            SocialLoginDivider("Or Register with")
            MainButton("Google", onClick = {})
        }
    }
}