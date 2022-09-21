package com.example.sp_techtest.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sp_techtest.model.Album
import com.example.sp_techtest.repo.AlbumRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val albumRepo: AlbumRepo
) : ViewModel() {
    //ViewModel class with hilt injection for API service, serves as a mediator between the model
    //and the UI. Holds list of albums as well as an error message in-case the
    //API does not respond.

    var albumList: List<Album> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    init {
        viewModelScope.launch {
            try {
                albumList = albumRepo.getAlbums()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}