package com.example.moviejetpackcompose.ui.theme.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.moviejetpackcompose.model.ResponseState
import com.example.moviejetpackcompose.pojo.MediaType
import com.example.moviejetpackcompose.pojo.Result
import com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel.HomeViewModel


@Composable
fun TrendingMoviesScreen(navController: NavController,viewModel: HomeViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    val topRated by viewModel.topRated.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getTrending()
        viewModel.getTopRated()
    }
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        item {
        ///Trending
    when (state) {
        is ResponseState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize() // This makes the Box take up all available space
                    .wrapContentSize(Alignment.Center) // Centers the content within the Box
            ) {
                CircularProgressIndicator()
            }
        }
        is ResponseState.Success -> {
            val trendingMovies = (state as ResponseState.Success).data.results


                Text(
                    text = "Trending Movies",
                    fontSize = 24.sp
                    , fontStyle = FontStyle.Italic,
                    modifier = Modifier.fillMaxWidth()
                    , color = Color.White
                )
                LazyRow {
                    items(trendingMovies) { movie ->
                        MovieItem(movie){
                            navController.navigate("details/${movie.title}/${movie.overview}")

                        }

                    }

            }
        }
        is ResponseState.Error -> {
            val exception = (state as ResponseState.Error)
            Text(text = "Error: ${exception.message}")
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
        ///TopRated
        when(topRated){
            is ResponseState.Loading->{

            }
            is ResponseState.Success->{
                val topRated=(topRated as ResponseState.Success).data.results

                Text(text = "Top Rated Movies"
                    , modifier = Modifier.fillMaxWidth()
                    , fontSize = 24.sp
                    , fontStyle = FontStyle.Italic
                    , color = Color.White)
                LazyRow {
                    items(topRated){movie->
                        MovieItem(movie) {
                            navController.navigate("details/${movie.title}/${movie.overview}")
                        }
                    }
                }
            }
            is ResponseState.Error->{
                val exception = (topRated as ResponseState.Error)
                Text(text = "Error: ${exception.message}")
            }
        }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Popular Movies", modifier = Modifier.fillMaxWidth(),
                fontStyle=FontStyle.Italic
                , fontSize = 25.sp,
                color = Color.White)
}

}

}

@Composable
fun MovieItem(movie: Result,onClick:()->Unit) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .size(200.dp, 300.dp)
            .clickable { onClick() }
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
