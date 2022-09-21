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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sp_techtest.model.Album
import com.example.sp_techtest.model.AlbumApiModel
import com.example.sp_techtest.ui.theme.SPTechTestTheme
import com.example.sp_techtest.viewmodel.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<AlbumViewModel>() //Viewmodel instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SPTechTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (viewModel.albumList.isEmpty()) { //Checks if album contains no elements
                        if (viewModel.errorMessage.isBlank()) {
                            //If there are no elements check if there is an error message
                            //If not assume we are still retrieving the data and display loading message
                            Text(text = "Retrieving data...", modifier = Modifier.padding(8.dp))
                        } else {
                            //If there is an error message, display it
                            Text(
                                text = "API Error: ${viewModel.errorMessage}",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    } else { //Else display list of elements
                        AlbumList(albumList = viewModel.albumList)

                    }

                }
            }
        }
    }
}

@Composable
fun AlbumList(albumList: List<Album>) {
    LazyColumn {
        itemsIndexed(albumList) { _, item ->
            AlbumItem(
                album = item
            )
        }
    }
}

@Composable
fun AlbumItem(album: Album) {
    //Single album item
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            )
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
        ) {
            Text(text = album.title)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumPreview() {
    val album = Album("My Beautiful Dark Twisted Fantasy")
    AlbumItem(album = album)
}
