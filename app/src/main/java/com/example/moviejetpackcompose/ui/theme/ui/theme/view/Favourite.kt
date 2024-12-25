package com.example.moviejetpackcompose.ui.theme.ui.theme.view

import android.net.Uri.encode
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.moviejetpackcompose.model.netWork.ResponseState
import com.example.moviejetpackcompose.model.pojo.MovieDataFav
import com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel.HomeViewModel
import com.google.gson.Gson
import java.net.URLEncoder

@Composable
fun FavouriteScreen(navController: NavController, viewModel: HomeViewModel) {
   val favMovieState = viewModel.fav.collectAsState()

   LaunchedEffect(Unit) {
      viewModel.getFAv()
   }

   val state = favMovieState.value
   when (state) {
      is ResponseState.Loading -> {
         CircularProgressIndicator(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary)
      }
      is ResponseState.Error -> {
         // Handle error (you can show a message here)
      }
      is ResponseState.Success -> {
         MovieList(fav = state.data, viewModel = viewModel, navController = navController)
      }
   }
}

@Composable
fun MovieList(fav: List<MovieDataFav>, viewModel: HomeViewModel, navController: NavController) {
   LazyColumn(modifier = Modifier.fillMaxSize()) {
      items(fav) { movie ->
         MovieCard(movieDataFav = movie, viewModel = viewModel, navController = navController)
      }
   }
}

@Composable
fun MovieCard(movieDataFav: MovieDataFav, viewModel: HomeViewModel, navController: NavController) {
   Card(
      modifier = Modifier
         .fillMaxWidth()
         .padding(16.dp)
         .clickable {
            val jsonResult = encode( Gson().toJson(movieDataFav))
            navController.navigate("details/$jsonResult")
         },
      shape = RoundedCornerShape(corner = CornerSize(12.dp)),
      elevation = CardDefaults.elevatedCardElevation(8.dp)
   ) {
      Row(
         modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
         verticalAlignment = Alignment.CenterVertically
      ) {
         // Image with rounded corners
         AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movieDataFav.posterPath}",
            contentDescription = null,
            modifier = Modifier
               .size(100.dp)

         )

         Spacer(modifier = Modifier.width(16.dp))

         // Movie title and description
         Column(
            modifier = Modifier.weight(1f)
         ) {
            movieDataFav.title?.let {
               Text(
                  text = it,
                  style = MaterialTheme.typography.titleLarge,
                  color = MaterialTheme.colorScheme.onBackground,
                  maxLines = 1,
                  modifier = Modifier.padding(bottom = 8.dp)
               )
            }
            movieDataFav.overView?.let {
               Text(
                  text = it,
                  style = MaterialTheme.typography.labelMedium,
                  color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                  maxLines = 2,
                  modifier = Modifier.padding(bottom = 8.dp)
               )
            }
         }


         IconButton(
            onClick = {
               viewModel.deletefromDB(movieDataFav)
            }
         ) {
            Icon(
               imageVector = Icons.Default.Delete,
               contentDescription = "Delete Movie",
               tint = Color.Red
            )
         }
      }
   }
}
