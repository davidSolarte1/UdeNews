package com.example.udenews.network

import com.example.udenews.model.News
import retrofit2.Call
import retrofit2.http.GET

interface ApiNews {
    @GET("api/news_rest/")
    fun getNews(): Call<List<News>>
}