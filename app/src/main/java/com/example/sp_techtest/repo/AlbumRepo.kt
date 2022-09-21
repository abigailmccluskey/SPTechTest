package com.example.sp_techtest.repo

import com.example.sp_techtest.model.Album
import com.example.sp_techtest.viewmodel.AlbumAPIService
import javax.inject.Inject

class AlbumRepo @Inject constructor(
    private val apiService: AlbumAPIService
) {
    suspend fun getAlbums(): List<Album> {
        val albumApiList = apiService.getAlbums()
        val albumList = albumApiList.map { Album(it.title) }
        return albumList.sortedBy { it.title }
    }
}