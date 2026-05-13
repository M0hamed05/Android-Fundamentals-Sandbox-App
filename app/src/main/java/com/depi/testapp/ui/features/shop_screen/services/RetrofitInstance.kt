package com.depi.testapp.ui.features.shop_screen.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object only one class
object RetrofitInstance {
    private const val BASE_URL = "https://api.escuelajs.co/api/v1/"
    val retrofit by lazy { //by lazy means use it only when you neeed it
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val Api: ApiService by lazy {
        retrofit.create(ApiService::class.java) //do the orders based on the retrofit
    }
}