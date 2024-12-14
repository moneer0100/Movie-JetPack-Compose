package com.example.moviejetpackcompose.ui.theme.ui.theme.view

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage

@Composable
fun DetailsMovies(movie: String?, moveDetails: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = "Movie Details",
            fontSize = 24.sp,
            color = Color.White
        )



        movie?.let {
            Text(
                text = "Title: $movie",
                fontSize = 20.sp,
                color = Color.Gray
            )
            Text(text = "Details: $moveDetails")
        }
    }
}






