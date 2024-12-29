package com.example.moviejetpackcompose.model.netWork

import com.example.moviejetpackcompose.model.pojo.TrendingPojo
import javax.inject.Inject

class MovieRemotImp @Inject constructor(private val apiService: ApiService) : MovieRemoteInterface {



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
