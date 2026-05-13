package com.depi.testapp.ui.features.shop_screen.services

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>
}

data class Product(
    val id:Int,
    val title:String,
    val slug:String,
    val price:Int,
    val description:String,
    val images:List<String>,

)