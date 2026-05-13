package com.depi.testapp.ui.features.shop_screen.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import com.depi.testapp.ui.features.shop_screen.view_model.HomeError
import com.depi.testapp.ui.features.shop_screen.view_model.HomeLoading
import com.depi.testapp.ui.features.shop_screen.view_model.HomeProducts
import com.depi.testapp.ui.features.shop_screen.view_model.HomeState
import com.depi.testapp.ui.features.shop_screen.view_model.ShopViewModel
import com.depi.testapp.ui.features.todolist.model.TodoItem

@Composable
fun ShopScreen(viewModel: ShopViewModel = viewModel()){
    /*
    Scaffold(
        modifier = Modifier.padding(bottom = 80.dp).padding(top = 70.dp),
        containerColor = Color.White
    ) { PaddingValues ->
        Column(
            modifier = Modifier.padding(PaddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn {
            items(items =)
            }
        }
    }

     */

    val currenState = viewModel.currentState.value
    val products = viewModel.products.value
    val errorMessage = viewModel.errorMessage.value

    when(currenState){
        HomeState.Loading -> HomeLoading()
        HomeState.Error -> HomeError(errorMessage)
        HomeState.Success -> HomeProducts(products)
    }

}