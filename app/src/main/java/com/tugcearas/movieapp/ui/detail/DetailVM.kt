package com.tugcearas.movieapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugcearas.movieapp.data.model.movie.FavoriteModel
import com.tugcearas.movieapp.data.model.movie.MovieModel
import com.tugcearas.movieapp.data.model.movie.WatchlistModel
import com.tugcearas.movieapp.data.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val movieRepo: MovieRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    private var _movie: MutableLiveData<MovieModel> = MutableLiveData()
    val movie get() = _movie

    init {
        getMovies()
    }

    private fun getMovies() = viewModelScope.launch {
        savedStateHandle.get<MovieModel>("movie")?.let {
            _movie.value = it
        }
    }

    fun addMovieToFavorite() = viewModelScope.launch {
        _movie.value?.let {
            movieRepo.addMovieToFavorite(
                FavoriteModel(it.id!!,it.posterPath!!,it.title!!)
            )
        }
    }

    fun addMovieToWatchlist() = viewModelScope.launch {
        _movie.value?.let {
            movieRepo.addMovieToWatchlist(
                WatchlistModel(it.id!!,it.posterPath!!,it.title!!)
            )
        }
    }
}