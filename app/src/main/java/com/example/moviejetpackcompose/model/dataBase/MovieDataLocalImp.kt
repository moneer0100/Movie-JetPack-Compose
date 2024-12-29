package com.example.moviejetpackcompose.model.dataBase

import com.example.moviejetpackcompose.model.pojo.MovieDataFav
import dagger.Module
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDataLocalImp @Inject constructor(private val dao:Dao):MovieDataLocalInterface {

    override fun getAllData(): Flow<List<MovieDataFav>> {
        return dao.getAllData()
    }

    override suspend fun insertFav(movieData: MovieDataFav) {
         dao.insertFav(movieData)
    }

    override suspend fun deleteFav(movieData: MovieDataFav) {
      dao.deleteFav(movieData)
    }
}