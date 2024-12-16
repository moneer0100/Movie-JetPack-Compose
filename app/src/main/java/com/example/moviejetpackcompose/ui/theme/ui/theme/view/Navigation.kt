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

import com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel.HomeViewModel

import com.example.moviejetpackcompose.ui.theme.view.TrendingMoviesScreen

@Composable
fun MovieNavigation(viewModel: HomeViewModel) {
    val navControl = rememberNavController()

    // استخدم Scaffold لتضمين BottomNavigationBar
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
            composable("details/{movieTitle}/{movieDetails}") { backStackEntry ->
                val movieTitle = backStackEntry.arguments?.getString("movieTitle")
                val movieDetails = backStackEntry.arguments?.getString("movieDetails")
                DetailsMovies(movieTitle, movieDetails)
            }
            composable("search") {
                SearchScreen()
            }
            composable("fav") {
                FavouriteScreen()
            }

        }
    }
}
@Composable
fun BottomNavigationBar(navController: NavController, modifier: Modifier = Modifier) {
    androidx.compose.material3.BottomAppBar(
        modifier = modifier.height(56.dp),
        containerColor = Color.Black // لون الخلفية
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
                        navController.popBackStack("trending", false) // إزالة أي شاشات فوق "trending"
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
                    // تنقل إلى شاشة المفضلة
                    navController.navigate("fav")
                }
            )
        }
    }
}



