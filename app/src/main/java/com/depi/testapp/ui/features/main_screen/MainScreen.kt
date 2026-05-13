package com.depi.testapp.ui.features.main_screen

import android.R
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.depi.testapp.constraint.enProfile
import com.depi.testapp.ui.features.main_screen.content_screen.ContentScreen
import com.depi.testapp.ui.features.main_screen.content_screen.NavigationBarItem
import com.depi.testapp.ui.theme.mainBlackColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController,profile: enProfile) {
    val navItems = listOf(
        NavigationBarItem("Todo",Icons.Default.Check),
        NavigationBarItem("Notes",Icons.Default.NoteAlt),
        NavigationBarItem("Stopwatch",Icons.Default.AccessTime),
        NavigationBarItem("Shop", Icons.Default.Shop),
        NavigationBarItem("Profile", Icons.Default.Person)
    )
    var selectedIndex by remember { mutableIntStateOf(0) }
    Scaffold(
        containerColor = Color.White,
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, navitem ->
                    NavigationBarItem(
                        selected = (selectedIndex == index),
                        onClick = {
                            selectedIndex = index
                        },
                        icon = { Icon(imageVector = navitem.icon, contentDescription = "") },
                        label = { Text(navitem.label) },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.White
                        )
                    )
                }
            }
        },

        topBar = {
            TopAppBar(
                title = { Text("Test App") },
                navigationIcon = {},
                actions = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = mainBlackColor
                )
            )
        }
    ) {
        innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding),selectedIndex, profile = profile,navController)
    }
}