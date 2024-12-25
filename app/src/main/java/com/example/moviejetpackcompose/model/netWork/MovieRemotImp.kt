package com.example.moviejetpackcompose.model.netWork

import com.example.moviejetpackcompose.model.pojo.TrendingPojo

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

    override suspend fun getPopular(): TrendingPojo {
        return apiService.getPopularMovies()
    }

    override suspend fun getDiscover(): TrendingPojo {
        return apiService.getDiscoverMovies()
    }

    override suspend fun getSearch(search:String): TrendingPojo {
        return apiService.getSearch(search=search)
    }
}
