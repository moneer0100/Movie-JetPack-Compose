package com.example.moviejetpackcompose.model.dataBase

import com.example.moviejetpackcompose.model.pojo.MovieDataFav
import kotlinx.coroutines.flow.Flow

class MovieDataLocalImp(private val dao:Dao):MovieDataLocalInterface {
    companion object {
        @Volatile
        var instance: MovieDataLocalImp? = null
        fun getInstance(dao: Dao): MovieDataLocalImp {
            return instance?: synchronized(this){
                instance?: MovieDataLocalImp(dao)
                    .also { instance = it }
            }
        }
    }

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