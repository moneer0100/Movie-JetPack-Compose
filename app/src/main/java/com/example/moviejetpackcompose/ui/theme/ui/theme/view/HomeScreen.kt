package com.example.moviejetpackcompose.ui.theme.ui.theme.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviejetpackcompose.model.dataBase.DataBaseClient
import com.example.moviejetpackcompose.model.dataBase.MovieDataLocalImp
import com.example.moviejetpackcompose.model.netWork.MovieRemotImp
import com.example.moviejetpackcompose.model.netWork.RetrofitHelper
import com.example.moviejetpackcompose.model.pojo.RepoMovieImp
import com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel.HomeFactory
import com.example.moviejetpackcompose.ui.theme.ui.theme.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val apiService = RetrofitHelper.apiService
//        val dataBase=DataBaseClient.getInstance(this).movieApp()
//        val repository = RepoMovieImp.RepositoryModule.provideMovieRemoteImp(apiService),
//            MovieDataLocalImp(dataBase)
//        val viewModelFactory = HomeFactory(repository)
        setContent {
            val viewModel: HomeViewModel = hiltViewModel()
            MovieNavigation(viewModel = viewModel)

        }

    }
}



