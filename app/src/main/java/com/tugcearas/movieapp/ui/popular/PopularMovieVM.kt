package com.tugcearas.movieapp.ui.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugcearas.movieapp.data.model.movie.MovieModel
import com.tugcearas.movieapp.data.repo.MovieRepository
import com.tugcearas.movieapp.util.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMovieVM @Inject constructor(val repo: MovieRepository):ViewModel() {

    val getMovies : MutableLiveData<Resource<List<MovieModel>>> = MutableLiveData()

    init {
        getPopularMovie()
    }

    private fun getPopularMovie() = viewModelScope.launch {
        getMovies.postValue(handleResponse(repo.getPopularMovie()))
    }

    private fun handleResponse(response:Resource<List<MovieModel>>) = when(response){
        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> Resource.Success(response.data.orEmpty())
        is Resource.Error -> Resource.Error(response.message.orEmpty())
    }
}