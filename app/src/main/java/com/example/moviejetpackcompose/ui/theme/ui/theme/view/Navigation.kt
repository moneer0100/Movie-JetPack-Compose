package com.example.moviejetpackcompose.ui.theme.ui.theme.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviejetpackcompose.model.pojo.MovieDataFav
import com.example.moviejetpackcompose.model.pojo.Result

import com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel.HomeViewModel

import com.example.moviejetpackcompose.ui.theme.view.TrendingMoviesScreen
import com.google.gson.Gson

@Composable
fun MovieNavigation(viewModel: HomeViewModel) {
    val navControl = rememberNavController()


    androidx.compose.material3.Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navControl)
        }
    ) { paddingValues ->
        NavHost(
            navController = navControl,
            startDestination = "trending",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("trending") {
                TrendingMoviesScreen(navController = navControl, viewModel = viewModel)
            }
            composable("details/{result}") { backStackEntry ->
                val jsonResult = backStackEntry.arguments?.getString("result")
                if (jsonResult != null) {
                    val movieResult = Gson().fromJson(jsonResult, Result::class.java)
                    Log.d("moneer", "MovieNavigation: $movieResult")
                    if (movieResult != null) {
                        DetailsMovies(movieResult = movieResult, viewModel = viewModel)
                    }
                } else {
                    Log.e("MovieNavigation", "Error: No movie result found")
                }

            }
            composable("search") {
                SearchScreen(navController = navControl, viewModel = viewModel)
            }
            composable("fav") {
                FavouriteScreen(navController=navControl,viewModel=viewModel)
            }

        }
    }
}
@Composable
fun BottomNavigationBar(navController: NavController, modifier: Modifier = Modifier) {
    androidx.compose.material3.BottomAppBar(
        modifier = modifier.height(56.dp),
        containerColor = Color.Black
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.material3.Icon(
                imageVector =  androidx.compose.material.icons.Icons.Default.Home
                , contentDescription = "Home",
                tint = Color.White,
                modifier = Modifier.clickable {
                    if (navController.currentBackStackEntry?.destination?.route != "trending") {
                        navController.popBackStack("trending", false)
                        navController.navigate("trending") {

                            popUpTo("trending") { inclusive = true }
                        }
                    }
                })
            androidx.compose.material3.Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.clickable {

                    navController.navigate("search")
                }
            )


            androidx.compose.material3.Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = Color.White,
                modifier = Modifier.clickable {

                    navController.navigate("fav")
                }
            )
        }
    }
}



