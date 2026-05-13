package com.depi.testapp.ui.features.shop_screen.view_model

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.depi.testapp.ui.features.shop_screen.services.Product
import com.depi.testapp.ui.features.shop_screen.services.RetrofitInstance
import kotlinx.coroutines.launch
import java.nio.file.WatchEvent

class ShopViewModel: ViewModel() {

    val currentState = mutableStateOf(HomeState.Loading)
    val products = mutableStateOf(listOf<Product>())
     val errorMessage = mutableStateOf("")

    init{
        loadProducts()
    }
    private fun loadProducts() {
        viewModelScope.launch {
            currentState.value = HomeState.Loading
            try {
                val response = RetrofitInstance.Api.getProducts()
                if (response.isSuccessful) {
                    products.value = response.body()!!
                    currentState.value = HomeState.Success
                } else {
                    currentState.value = HomeState.Error
                    errorMessage.value = "Error"
                    Log.e("response","Error ${response.body().toString()}")
                }
            } catch (e: Exception) {
                currentState.value = HomeState.Error
                errorMessage.value = "Exception : ${e.message.toString()}"
                Log.e("response", "Exception ${e.message.toString()}")
            }
        }
    }
}

@Composable
fun HomeLoading(){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CircularProgressIndicator()
    }
}

@Composable
fun HomeError(messageError:String){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(messageError, fontSize = 14.sp)
    }
}

@Composable
fun HomeProducts(products: List<Product>) {
    Scaffold(
        modifier = Modifier.padding(bottom = 80.dp).padding(top = 70.dp),
        containerColor = Color.White
    ) { PaddingValues ->
        Column(
            modifier = Modifier.padding(PaddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
                items(items = products) { item ->
                    Card(
                        modifier = Modifier.padding(8.dp).fillMaxWidth().padding(8.dp)
                    ) {
                            Column {
                                Text(item.title, fontSize = 15.sp, modifier = Modifier.padding(8.dp))
                                Text(item.price.toString(), fontSize = 13.sp, modifier = Modifier.padding(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }


enum class HomeState{
    Loading,Success,Error
}
