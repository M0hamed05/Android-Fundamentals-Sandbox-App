package com.depi.testapp.ui.features.main_screen.content_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.depi.testapp.constraint.enProfile
import com.depi.testapp.ui.features.profile_screen.ProfileScreen
import com.depi.testapp.ui.features.shop_screen.model.ShopScreen
import com.depi.testapp.ui.features.notes.model.NoteScreen
import com.depi.testapp.ui.features.stop_watch.StopWatchScreen
import com.depi.testapp.ui.features.todolist.model.TodoListScreen

@Composable
fun ContentScreen(modifier: Modifier = Modifier,selectedIndex:Int,profile: enProfile,navController: NavController) {
    when (selectedIndex) {
        0 -> TodoListScreen()
        1 -> NoteScreen()
        2 -> StopWatchScreen()
        3 -> ShopScreen()
        4 -> ProfileScreen(profile, onBack = { navController.popBackStack() })
    }
}