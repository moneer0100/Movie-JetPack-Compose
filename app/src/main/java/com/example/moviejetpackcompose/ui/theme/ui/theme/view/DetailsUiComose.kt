package com.example.moviejetpackcompose.ui.theme.ui.theme.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.IconButton
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviejetpackcompose.model.pojo.MovieDataFav
import com.example.moviejetpackcompose.model.pojo.Result
import com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel.HomeViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun DetailsMovies(movieResult: Result, viewModel: HomeViewModel) {
    var isFavorite by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Movie Title
        Text(
            text = "Movie Details",
            fontSize = 24.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Movie Information in a Grid with 2 items per row
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 items per row
            contentPadding = PaddingValues(8.dp)
        ) {
            item {
                // Movie Title
                Text(
                    text = "Title: ${movieResult.title}",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }

            item {
                // Movie Overview
                Text(
                    text = "Overview: ${movieResult.overview}",
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }

            item {

                IconButton(
                    onClick = {
                        isFavorite = !isFavorite
                        if (isFavorite && movieResult != null) {
                            val movieDataFav = MovieDataFav(
                                id = movieResult.id,
                                title = movieResult.title,
                                overView = movieResult.overview,
                                posterPath = movieResult.posterPath
                            )
                            viewModel.insertMovieInFav(movieDataFav)
                        } else if (!isFavorite && movieResult != null) {

                        }
                    },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = if (isFavorite) "Remove from fav" else "Add to fav",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }
        }
    }
}
