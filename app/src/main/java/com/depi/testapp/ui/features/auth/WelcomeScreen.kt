package com.depi.testapp.ui.features.auth

import android.R.attr.contentDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.depi.testapp.R
import com.depi.testapp.constraint.routes
import com.depi.testapp.ui.components.MainButton
import com.depi.testapp.ui.theme.mainBlackColor

@Composable
fun WelcomeScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatarimage),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                contentScale = ContentScale.Crop
            )
            MainButton("Login", padding = 5, onClick = {navController.navigate(routes.loginScreen)})
            MainButton(
                "Register",
                containerColor = Color.White,
                contentColor = mainBlackColor,
                padding = 5,
                onClick = {navController.navigate(routes.registerScreen)})
            Text(
                "Continue as a guest",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 50.dp).clickable(onClick = {
                    navController.navigate("${routes.mainScreen}/geust")
                })
            )
        }
    }
}


