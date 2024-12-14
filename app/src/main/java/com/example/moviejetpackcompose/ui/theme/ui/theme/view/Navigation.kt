package com.example.moviejetpackcompose.ui.theme.ui.theme.view

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel.HomeViewModel
import com.example.moviejetpackcompose.ui.theme.view.TrendingMoviesScreen

@Composable
fun MovieNavigation(viewModel: HomeViewModel){
    val navControl= rememberNavController()
    NavHost(navController = navControl, startDestination = "trending" ){
        composable("trending") {
            TrendingMoviesScreen(navControl,viewModel)
        }
        composable("details/{movieTitle}/{moveDetails}") { backStackEntry ->
            val movieTitle = backStackEntry.arguments?.getString("movieTitle")
            val moveDetails=backStackEntry.arguments?.getString("moveDetails")


            Log.d("moneer", "MovieNavigation:$movieTitle/$moveDetails ")

         DetailsMovies(movie = movieTitle, moveDetails =moveDetails )

        }
    }
}