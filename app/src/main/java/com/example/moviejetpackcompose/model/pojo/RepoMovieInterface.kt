package com.example.moviejetpackcompose.model.pojo

import kotlinx.coroutines.flow.Flow

interface RepoMovieInterface {
    suspend fun getTrending(): Flow<TrendingPojo>
    suspend fun getTopRating():Flow<TrendingPojo>
    suspend fun getPopular():Flow<TrendingPojo>
    suspend fun getDiscover():Flow<TrendingPojo>
    suspend fun getSearch(search:String):Flow<TrendingPojo>
///fav
fun getAllFavMovie():Flow<List<MovieDataFav>>
suspend fun insertMovieToDav(movieData: MovieDataFav)
suspend fun deleteMovieFromFav(movieData: MovieDataFav)
}