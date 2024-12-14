package com.example.moviejetpackcompose.ui.theme.ui.theme.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviejetpackcompose.model.MovieRemotImp
import com.example.moviejetpackcompose.model.MovieRemoteInterface
import com.example.moviejetpackcompose.model.RetrofitHelper
import com.example.moviejetpackcompose.pojo.RepoMovieImp
import com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel.HomeFactory
import com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel.HomeViewModel
import com.example.moviejetpackcompose.ui.theme.view.TrendingMoviesScreen

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService = RetrofitHelper.apiService
        val repository = RepoMovieImp.getInstance(MovieRemotImp.getInstance(apiService))
        val viewModelFactory = HomeFactory(repository)
        setContent {
            val viewModel: HomeViewModel = viewModel(factory = viewModelFactory)
            MovieNavigation(viewModel)
            }
        }
    }




