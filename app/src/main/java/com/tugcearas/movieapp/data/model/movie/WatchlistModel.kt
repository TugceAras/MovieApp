package com.tugcearas.movieapp.data.model.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("watchlistTable")
data class WatchlistModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id:Int,

    @SerializedName("poster_path")
    @ColumnInfo("poster_path")
    val posterPath: String,

    @ColumnInfo("title")
    val title: String
)
