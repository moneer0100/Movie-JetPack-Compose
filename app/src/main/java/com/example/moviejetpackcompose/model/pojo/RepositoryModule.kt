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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieDataLocalImp(dao: Dao): MovieDataLocalInterface {
        return MovieDataLocalImp(dao)
    }
    @Singleton
    @Provides
    fun provideMovieRemoteImp(apiService: ApiService): MovieRemoteInterface {
        return MovieRemotImp(apiService)
    }
}