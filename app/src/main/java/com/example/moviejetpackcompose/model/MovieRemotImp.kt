package com.example.moviejetpackcompose.model

import com.example.moviejetpackcompose.pojo.TrendingPojo

class MovieRemotImp private constructor(private val apiService: ApiService) : MovieRemoteInterface {

    companion object {
        @Volatile
        private var instance: MovieRemotImp? = null

        fun getInstance(apiService: ApiService): MovieRemotImp {
            return instance ?: synchronized(this) {
                instance ?: MovieRemotImp(apiService).also { instance = it }
            }
        }
    }

    override suspend fun getTrending(): TrendingPojo {
        return apiService.getTrending()
    }

    override suspend fun getTopRated(): TrendingPojo {
        return apiService.getTopRated()
    }
}
