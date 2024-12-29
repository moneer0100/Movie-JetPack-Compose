package com.example.moviejetpackcompose.model.pojo

import com.example.moviejetpackcompose.model.dataBase.Dao
import com.example.moviejetpackcompose.model.dataBase.MovieDataLocalImp
import com.example.moviejetpackcompose.model.dataBase.MovieDataLocalInterface
import com.example.moviejetpackcompose.model.netWork.ApiService
import com.example.moviejetpackcompose.model.netWork.MovieRemotImp
import com.example.moviejetpackcompose.model.netWork.MovieRemoteInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoMovieImp @Inject constructor ( private val movieRemoteInterface: MovieRemoteInterface,
    private val movieDataLocalInterface: MovieDataLocalInterface): RepoMovieInterface {

    override suspend fun getTrending(): Flow<TrendingPojo> {
        return flowOf(movieRemoteInterface.getTrending())
    }

    override suspend fun getTopRating(): Flow<TrendingPojo> {
        return flowOf( movieRemoteInterface.getTopRated())
    }

    override suspend fun getPopular(): Flow<TrendingPojo> {
        return flowOf(movieRemoteInterface.getPopular())
    }

    override suspend fun getDiscover(): Flow<TrendingPojo> {
       return flowOf(movieRemoteInterface.getDiscover())
    }

    override suspend fun getSearch(search:String): Flow<TrendingPojo> {
        return flowOf(movieRemoteInterface.getSearch(search=search ))
    }

    override fun getAllFavMovie(): Flow<List<MovieDataFav>> {
        return movieDataLocalInterface.getAllData()
    }

    override suspend fun insertMovieToDav(movieData: MovieDataFav) {
      movieDataLocalInterface.insertFav(movieData)
    }

    override suspend fun deleteMovieFromFav(movieData: MovieDataFav) {
         movieDataLocalInterface.deleteFav(movieData)
    }
}
