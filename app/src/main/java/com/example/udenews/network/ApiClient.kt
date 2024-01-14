package com.example.udenews.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.13:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val consApi = retrofit.create(ApiNews::class.java)
}