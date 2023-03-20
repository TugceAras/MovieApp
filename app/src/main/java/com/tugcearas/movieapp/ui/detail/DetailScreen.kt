package com.tugcearas.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tugcearas.movieapp.R
import com.tugcearas.movieapp.data.model.movie.MovieModel
import com.tugcearas.movieapp.databinding.FragmentDetailScreenBinding
import com.tugcearas.movieapp.util.extensions.click
import com.tugcearas.movieapp.util.extensions.toastMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreen : Fragment() {

    private lateinit var binding: FragmentDetailScreenBinding
    private val detailViewModel : DetailVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_screen,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            detailWatchlistButton.click {
                detailWatchlistButton.setImageResource(R.drawable.watchlist_fill_icon)
                detailViewModel.addMovieToWatchlist()
                requireContext().toastMessage(getString(R.string.click_watchlist_icon))
            }

            detailFavButton.click {
                detailFavButton.setImageResource(R.drawable.fav_fill_icon)
                detailViewModel.addMovieToFavorite()
                requireContext().toastMessage(getString(R.string.click_fav_icon))
            }

            detailToolbar.customToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
        initObserve()
    }

    private fun initObserve(){
        detailViewModel.movie.observe(viewLifecycleOwner){
            binding.movie = it
        }
    }
}