package com.example.moviejetpackcompose.ui.theme.view

import android.net.Uri.encode
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviejetpackcompose.model.netWork.ResponseState
import com.example.moviejetpackcompose.model.pojo.Result
import com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel.HomeViewModel
import com.google.gson.Gson
import java.net.URLEncoder


@Composable
fun TrendingMoviesScreen(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    val topRated by viewModel.topRated.collectAsState()
    val popular by viewModel.popular.collectAsState()
    val discover by viewModel.discover.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getTrending()
        viewModel.getTopRated()
        viewModel.getPopularData()
        viewModel.getDiscoverData()

    }
    Box(modifier = Modifier.fillMaxSize()) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        item {
            // Trending Movies
            when (state) {
                is ResponseState.Loading -> {
                    LoadingState()
                }
                is ResponseState.Success -> {
                    val trendingMovies = (state as ResponseState.Success).data.results
                    SectionTitle("Trending Movies")
                    MovieSection(movie = trendingMovies, navController = navController)
                }
                is ResponseState.Error -> {
                    ErrorState((state as ResponseState.Error).message.toString())
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp)) // Spacing between sections

            // Top Rated Movies
            when (topRated) {
                is ResponseState.Loading -> {
                    LoadingState()
                }
                is ResponseState.Success -> {
                    val topRatedMovies = (topRated as ResponseState.Success).data.results
                    SectionTitle("Top Rated Movies")
                    MovieSection(movie = topRatedMovies, navController = navController)
                }
                is ResponseState.Error -> {
                    ErrorState((topRated as ResponseState.Error).message.toString())
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp)) // Spacing between sections

            // Popular Movies
            when (popular) {
                is ResponseState.Loading -> {
                    LoadingState()
                }
                is ResponseState.Success -> {
                    val popularMovies = (popular as ResponseState.Success).data.results
                    SectionTitle("Popular Movies")
                    MovieSection(movie = popularMovies, navController = navController)
                }
                is ResponseState.Error -> {
                    ErrorState((popular as ResponseState.Error).message.toString())
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp)) // Spacing between sections

            // Discover Movies
            when (discover) {
                is ResponseState.Loading -> {
                    LoadingState()
                }
                is ResponseState.Success -> {
                    val discoverMovies = (discover as ResponseState.Success).data.results
                    SectionTitle("Discover Movies")
                    MovieSection(movie = discoverMovies, navController = navController)
                }
                is ResponseState.Error -> {
                    ErrorState((discover as ResponseState.Error).message.toString())
                }
            }
        }
    }

}}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorState(message: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(16.dp)
    ) {
        Text(text = "Error: $message", color = Color.Red)
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 24.sp,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.fillMaxWidth(),
        color = Color.Black
    )
}

@Composable
fun MovieSection(movie: List<Result>, navController: NavController) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movie) { movie ->
            MovieItem(movie = movie) {
                val jsonResult = encode(Gson().toJson(movie))
                navController.navigate("details/${jsonResult}")

            }
        }
    }
}
@Composable
fun MovieSectionSearch(movie:List<Result>, navController: NavController){
    LazyColumn(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        items(movie){movie->
            MovieItem(movie = movie) {
                val jsonResult = encode(Gson().toJson(movie))
                navController.navigate("details/$jsonResult")


            }
        }

    }
}


@Composable
fun MovieItem(movie: Result, onClick: (Result) -> Unit) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .size(200.dp, 300.dp)
            .clickable { onClick(movie) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
        }
    }
}