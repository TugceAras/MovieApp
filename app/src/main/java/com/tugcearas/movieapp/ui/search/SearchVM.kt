package com.tugcearas.movieapp.ui.search

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
class SearchVM @Inject constructor(private val movieRepo: MovieRepository) : ViewModel() {

    val searchNews: MutableLiveData<Resource<List<MovieModel>>> = MutableLiveData()

    fun searchMovies(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        searchNews.postValue(handleSearchNewsResponse(movieRepo.searchMovie(searchQuery)))
    }

    private fun handleSearchNewsResponse(response: Resource<List<MovieModel>>) = when (response) {
        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> Resource.Success(response.data.orEmpty())
        is Resource.Error -> Resource.Error(response.message.orEmpty())
    }
}