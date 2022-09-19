package com.example.sp_techtest.model

data class Album(val user_id: Int, val album_id: Int, val album_title: String) {
    var userID: Int = user_id
    var id: Int = album_id
    var title: String = album_title
}