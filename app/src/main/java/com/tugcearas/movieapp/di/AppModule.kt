package com.tugcearas.movieapp.di

import android.content.Context
import androidx.room.Room
import com.tugcearas.movieapp.data.source.local.MovieDao
import com.tugcearas.movieapp.data.source.local.MovieDatabase
import com.tugcearas.movieapp.data.source.remote.MovieApi
import com.tugcearas.movieapp.util.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit:Retrofit):MovieApi{
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):MovieDatabase =
        Room.databaseBuilder(context,MovieDatabase::class.java,"movieDatabase")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideDao(database:MovieDatabase):MovieDao{
        return database.getMovieFromDao()
    }
}