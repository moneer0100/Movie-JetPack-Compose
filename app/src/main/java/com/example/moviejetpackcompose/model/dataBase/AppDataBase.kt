package com.example.moviejetpackcompose.model.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviejetpackcompose.model.pojo.MovieDataFav
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [MovieDataFav::class], version = 1, exportSchema = false)

abstract class AppDataBase : RoomDatabase() {

    abstract fun movieApp(): Dao
}

@Module
@InstallIn(SingletonComponent::class)
object DataBaseClient {
    @Volatile
    private var instance: AppDataBase? = null

    @Singleton
    @Provides
    fun getInstance(
        @ApplicationContext
        context: Context): AppDataBase {
        return instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "favoriteMovies_db"
            ).build().also { instance = it }
        }
    }
    @Singleton
    @Provides
    fun provideDao(database: AppDataBase): Dao {
        return database.movieApp()
    }

}
