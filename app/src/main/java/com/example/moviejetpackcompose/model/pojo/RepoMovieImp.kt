package com.example.moviejetpackcompose.model.pojo

import com.example.moviejetpackcompose.model.dataBase.MovieDataLocalInterface
import com.example.moviejetpackcompose.model.netWork.MovieRemoteInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RepoMovieImp (private val movieRemoteInterface: MovieRemoteInterface,
    private val movieDataLocalInterface: MovieDataLocalInterface): RepoMovieInterface {
    companion object {
        private var instance: RepoMovieImp? = null
        fun getInstance(movieRemoteInterface: MovieRemoteInterface,
                        movieDataLocalInterface: MovieDataLocalInterface): RepoMovieImp {
            return instance ?: synchronized(this) {
                instance ?: RepoMovieImp(movieRemoteInterface,movieDataLocalInterface)
                    .also { instance = it }
            }
        }}

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
