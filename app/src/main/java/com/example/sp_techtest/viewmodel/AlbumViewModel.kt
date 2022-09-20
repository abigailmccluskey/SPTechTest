package com.example.sp_techtest.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sp_techtest.model.Album
import kotlinx.coroutines.launch

class AlbumViewModel : ViewModel() {
    var albumList:List<Album> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getAlbumList() {
        viewModelScope.launch {
            val apiService = AlbumAPIService.getInstance().create(AlbumAPIService::class.java)
            try {
                var albumResponse = apiService.getAlbums().sortedByDescending { it.title }
                albumList = albumResponse.reversed()
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}