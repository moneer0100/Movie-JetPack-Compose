package com.example.moviejetpackcompose.model.netWork

import com.example.moviejetpackcompose.model.pojo.TrendingPojo

interface MovieRemoteInterface {
    suspend fun getTrending(): TrendingPojo
    suspend fun getTopRated(): TrendingPojo
    suspend fun getPopular(): TrendingPojo
    suspend fun getDiscover(): TrendingPojo
    suspend fun getSearch(search:String): TrendingPojo
}