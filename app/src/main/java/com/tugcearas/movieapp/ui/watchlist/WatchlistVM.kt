package com.tugcearas.movieapp.ui.watchlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugcearas.movieapp.data.model.movie.WatchlistModel
import com.tugcearas.movieapp.data.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistVM @Inject constructor(private val movieRepo: MovieRepository): ViewModel() {

    val getWatchlistMovies: MutableLiveData<List<WatchlistModel>> = MutableLiveData()

    init {
        getWatchlists()
    }

    fun getWatchlists() = viewModelScope.launch {
        getWatchlistMovies.postValue(movieRepo.getMovieToWatchlist())
    }
}