package com.tugcearas.movieapp.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tugcearas.movieapp.R
import com.tugcearas.movieapp.data.model.movie.MovieModel
import com.tugcearas.movieapp.databinding.FragmentPopularMovieScreenBinding
import com.tugcearas.movieapp.ui.popular.adapter.PopularMovieAdapter
import com.tugcearas.movieapp.util.extensions.toastMessage
import com.tugcearas.movieapp.util.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMovieScreen : Fragment() {

    private lateinit var binding: FragmentPopularMovieScreenBinding
    private val popularMovieViewModel: PopularMovieVM by viewModels()
    private val popularMovieAdapter by lazy { PopularMovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPopularMovieScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    private fun initObserve() {
        popularMovieViewModel.getMovies.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    if (!response.data.isNullOrEmpty()) initAdapter(response.data)
                }
                else -> {
                    requireContext().toastMessage(response.message.orEmpty())
                }
            }
        }
    }

    private fun initAdapter(list: List<MovieModel>) {
        binding.popularMovieRecyclerview.adapter = popularMovieAdapter
        popularMovieAdapter.submitList(list)
    }
}