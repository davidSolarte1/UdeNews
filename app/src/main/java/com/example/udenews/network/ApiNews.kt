package com.example.udenews.network

import com.example.udenews.model.News
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiNews {
    @GET("api/news_rest/")
    fun getNews(): Call<List<News>>

    @POST("api/news_rest/")
    fun uploadNews(
        @Part("title") title: RequestBody,
        @Part image: MultipartBody.Part,
        @Part("date") date: RequestBody,
        @Part("place") place: RequestBody
    ): Call<News>
}