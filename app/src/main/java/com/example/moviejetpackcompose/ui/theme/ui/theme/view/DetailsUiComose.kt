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
import coil.compose.AsyncImage
import com.example.moviejetpackcompose.model.pojo.MovieDataFav
import com.example.moviejetpackcompose.model.pojo.Result
import com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel.HomeViewModel

@Composable
fun DetailsMovies(movieResult: Result, movieDataFav: MovieDataFav, viewModel: HomeViewModel) {
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
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Movie Poster and Information
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            // Movie Poster Image
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movieResult.posterPath}/https://image.tmdb.org/t/p/w500${movieDataFav.posterPath}",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(8.dp)

            )

            // Favorite Button
            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                    if (isFavorite) {
                        val movieDataFav = MovieDataFav(
                            id = movieResult.id,
                            title = movieResult.title,
                            overView = movieResult.overview,
                            posterPath = movieResult.posterPath,
                            mediaType = movieResult.mediaType.toString()
                        )
                        viewModel.insertMovieInFav(movieDataFav)
                    } else {
                        // Handle removing from favorites
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = if (isFavorite) "Remove from fav" else "Add to fav",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }

        // Movie Information in a Grid with 2 items per row
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                // Movie Title
                Text(
                    text = "Title: ${movieResult.title}/${movieDataFav.title}",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }

            item {
                // Movie Overview
                Text(
                    text = "Overview: ${movieResult.overview}/${movieDataFav.overView}",
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

