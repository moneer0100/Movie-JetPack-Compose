package com.example.moviejetpackcompose.model.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviejetpackcompose.model.pojo.MovieDataFav

@Database(entities = [MovieDataFav::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun movieApp(): Dao
}

object DataBaseClient {
    @Volatile
    private var instance: AppDataBase? = null

    fun getInstance(context: Context): AppDataBase {
        return instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "favoriteMovies_db"
            ).build().also { instance = it }
        }
    }
}
