package com.tugcearas.movieapp.ui.watchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tugcearas.movieapp.R
import com.tugcearas.movieapp.databinding.FragmentWatchlistScreenBinding
import com.tugcearas.movieapp.ui.watchlist.adapter.WatchlistAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchlistScreen : Fragment() {

    private lateinit var binding: FragmentWatchlistScreenBinding
    private val watchlistViewModel: WatchlistVM by viewModels()
    private val watchlistAdapter by lazy { WatchlistAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchlistScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initObserve()
        clickBackButton()
    }

    private fun initAdapter(){
        binding.watchlistRecyclerview.adapter = watchlistAdapter
    }

    private fun initObserve(){
        watchlistViewModel.getWatchlistMovies.observe(viewLifecycleOwner){
            watchlistAdapter.submitList(it)
        }
    }

    private fun clickBackButton(){
        binding.watchlistToolbar.customToolbar.setNavigationOnClickListener {
            val action = WatchlistScreenDirections.actionWatchlistScreenToSearchScreen()
            findNavController().navigate(action)
        }
    }

}