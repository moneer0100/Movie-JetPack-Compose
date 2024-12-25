package com.example.moviejetpackcompose.model.dataBase

import com.example.moviejetpackcompose.model.pojo.MovieDataFav
import kotlinx.coroutines.flow.Flow


interface MovieDataLocalInterface {
    fun getAllData(): Flow<List<MovieDataFav>>
    suspend fun insertFav(movieData: MovieDataFav)
    suspend fun deleteFav(movieData: MovieDataFav)
}