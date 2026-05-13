package com.depi.testapp.ui.features.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.depi.testapp.R
import com.depi.testapp.ui.components.BackButton
import com.depi.testapp.ui.components.HeadlineWord
import com.depi.testapp.ui.components.MainButton
import com.depi.testapp.ui.components.MainTextFieldEmail
import com.depi.testapp.ui.components.MainTextFieldPassword
import com.depi.testapp.ui.components.SocialLoginDivider
import com.depi.testapp.ui.theme.mainBlackColor

@Composable
fun LoginScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }
    var Email by remember { mutableStateOf("") }
    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BackButton (onClick = {navController.popBackStack()})
            HeadlineWord(stringResource(R.string.welcomeheadlineWord))
            MainTextFieldEmail(EmailValue = Email, OnEmailChanged = { Email = it })
            MainTextFieldPassword(
                passwordValue = password,
                passwordVisible = isVisible,
                onPasswordChange = { password = it },
                onToggleVisible = { isVisible = !isVisible })
            MainButton(stringResource(R.string.loginWord), onClick = {})
            SocialLoginDivider(stringResource(R.string.OrloginWith))

            MainButton("Google", onClick = {})
        }
    }
}