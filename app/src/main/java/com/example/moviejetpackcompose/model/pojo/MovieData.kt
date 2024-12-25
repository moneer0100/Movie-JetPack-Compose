package com.example.moviejetpackcompose.model.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favMovie")
data class MovieDataFav(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String?=null,
    val overView: String? =null,
    val posterPath: String?=null,
    val mediaType: String?=null
)
