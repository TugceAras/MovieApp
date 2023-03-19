package com.tugcearas.movieapp.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tugcearas.movieapp.data.model.movie.MovieModel
import com.tugcearas.movieapp.databinding.FragmentSearchScreenBinding
import com.tugcearas.movieapp.ui.search.adapter.SearchAdapter
import com.tugcearas.movieapp.util.extensions.click
import com.tugcearas.movieapp.util.extensions.gone
import com.tugcearas.movieapp.util.extensions.toastMessage
import com.tugcearas.movieapp.util.resource.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchScreen : Fragment() {

    private lateinit var binding: FragmentSearchScreenBinding
    private val searchViewModel: SearchVM by viewModels()
    private val searchAdapter by lazy { SearchAdapter(onSearchClick = ::onSearchClick) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search()
        initObserve()
        clickCancelButton()
        clickClearButton()
        clickBackButton()
    }

    private fun search() {
        var job: Job? = null
        binding.searchEditText.addTextChangedListener { editable ->
            // cancel the previous background task
            job?.cancel()
            job = MainScope().launch {
                binding.cancelButton.isVisible = editable.toString().isNotEmpty()
                delay(500L)
                if (editable.toString().isNotEmpty()) {
                    searchViewModel.searchMovies(editable.toString())
                }
            }
        }
    }

    private fun initObserve() {
        searchViewModel.searchNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        initAdapter(it)
                    }
                }
                is Resource.Error->{
                    requireContext().toastMessage(response.message.orEmpty())
                }
                else -> {}
            }
        }
    }

    private fun initAdapter(list: List<MovieModel>) {
        binding.searchRecyclerview.adapter = searchAdapter
        searchAdapter.submitList(list)
    }

    private fun clickCancelButton(){
        binding.cancelButton.setOnClickListener {
            binding.cancelButton.gone()
            it.hideKeyboard()
        }
    }

    private fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun clickClearButton(){
        with(binding){
            clearButton.click {
                searchEditText.setText("")
            }
        }

        // TODO : Delete operation for screen
    }

    private fun clickBackButton(){
        binding.searchToolbar.customToolbar.setNavigationOnClickListener {
           val action = SearchScreenDirections.actionSearchScreenToPopularMovieScreen()
            findNavController().navigate(action)
        }
    }

    private fun onSearchClick(movie: MovieModel) {
        val action = SearchScreenDirections.actionSearchScreenToDetailScreen(movie)
        findNavController().navigate(action)
    }
}