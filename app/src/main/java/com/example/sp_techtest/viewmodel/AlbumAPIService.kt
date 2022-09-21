package com.example.sp_techtest.viewmodel

import com.example.sp_techtest.model.AlbumApiModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
interface AlbumAPIService {
    @GET("albums")
    suspend fun getAlbums() : List<AlbumApiModel>

    companion object {
        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}