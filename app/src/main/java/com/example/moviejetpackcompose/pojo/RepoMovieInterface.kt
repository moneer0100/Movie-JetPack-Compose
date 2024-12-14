package com.example.moviejetpackcompose.pojo

import kotlinx.coroutines.flow.Flow

interface RepoMovieInterface {
    suspend fun getTrending(): Flow<TrendingPojo>
    suspend fun getTopRating():Flow<TrendingPojo>
    suspend fun getPopular():Flow<TrendingPojo>
}