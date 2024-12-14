package com.example.moviejetpackcompose.model

import com.example.moviejetpackcompose.pojo.TrendingPojo

interface MovieRemoteInterface {
    suspend fun getTrending(): TrendingPojo
    suspend fun getTopRated():TrendingPojo
}