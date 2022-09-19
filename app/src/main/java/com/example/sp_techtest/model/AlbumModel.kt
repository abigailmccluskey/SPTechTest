package com.example.sp_techtest.model

sealed class Album(userID: Int, id: Int, title: String) {
    var user_id: Int = userID
    var album_id: Int = id
    var album_title: String = title
}