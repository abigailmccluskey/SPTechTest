package com.example.sp_techtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sp_techtest.model.Album
import com.example.sp_techtest.ui.theme.SPTechTestTheme
import com.example.sp_techtest.viewmodel.AlbumViewModel
import android.util.Log
import kotlin.math.log


class MainActivity : ComponentActivity() {
    val albumVM by viewModels<AlbumViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SPTechTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AlbumList(albumList = albumVM.albumList)
                    albumVM.getAlbumList()
                }
            }
        }
    }
}

@Composable
fun AlbumList(albumList: List<Album>) {
    LazyColumn {
        itemsIndexed(items = albumList) { index, item ->
            AlbumItem(album = item)
        }
    }
}

@Composable
fun AlbumItem(album: Album) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(50.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface(color = Color.White) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
            ) {
                Text(text = "${album.title}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumPreview() {
    val album = Album(
        1,
        1,
        "My Beautiful Dark Twisted Fantasy",
    )
    AlbumItem(album = album)
}
