package com.tugcearas.movieapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tugcearas.movieapp.data.model.movie.FavoriteModel
import com.tugcearas.movieapp.data.model.movie.WatchlistModel

@Database(entities = [FavoriteModel::class,WatchlistModel::class], version = 1, exportSchema = false)
abstract class MovieDatabase:RoomDatabase() {

    abstract fun getMovieFromDao():MovieDao
}