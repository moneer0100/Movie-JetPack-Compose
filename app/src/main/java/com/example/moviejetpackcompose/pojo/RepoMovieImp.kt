package com.example.moviejetpackcompose.pojo

import com.example.moviejetpackcompose.model.MovieRemoteInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RepoMovieImp (private val movieRemoteInterface: MovieRemoteInterface):RepoMovieInterface {
    companion object {
        private var instance: RepoMovieImp? = null
        fun getInstance(movieRemoteInterface: MovieRemoteInterface ): RepoMovieImp {
            return this.instance ?: synchronized(this) {
                this.instance ?: RepoMovieImp(movieRemoteInterface)
                    .also { this.instance = it }
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
}
