package com.example.sp_techtest.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sp_techtest.model.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val apiService: AlbumAPIService
) : ViewModel() {

    var albumList: List<Album> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    init {
        viewModelScope.launch {
            try {
                albumList = apiService.getAlbums().sortedBy { it.title }
            } catch (e: Exception) {
                Log.d("TAG", e.message.toString())
                errorMessage = e.message.toString()
            }
        }
    }
}