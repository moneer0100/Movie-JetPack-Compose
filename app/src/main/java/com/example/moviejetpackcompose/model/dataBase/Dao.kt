package com.example.moviejetpackcompose.model.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviejetpackcompose.model.pojo.MovieDataFav
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM favMovie")
    fun getAllData(): Flow<List<MovieDataFav>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFav(movie: MovieDataFav)

    @Delete
    suspend fun deleteFav(movie: MovieDataFav)
}
