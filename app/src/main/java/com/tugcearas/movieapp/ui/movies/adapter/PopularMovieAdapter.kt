package com.tugcearas.movieapp.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tugcearas.movieapp.data.model.movie.MovieModel
import com.tugcearas.movieapp.databinding.PopularMovieItemBinding
import com.tugcearas.movieapp.util.constants.Constants
import com.tugcearas.movieapp.util.diffutil.DiffUtilCallback

class PopularMovieAdapter: ListAdapter<MovieModel, PopularMovieAdapter.PopularMovieViewHolder>(
    DiffUtilCallback<MovieModel>(
        itemsTheSame = { oldItem, newItem ->
            oldItem == newItem
        },
        contentsTheSame = { oldItem, newItem ->
            oldItem == newItem
        }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PopularMovieViewHolder(
        PopularMovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: PopularMovieAdapter.PopularMovieViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class PopularMovieViewHolder(private val binding:PopularMovieItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(movie:MovieModel) {
            with(binding){
                movieTitle.text = movie.title
                date.text = movie.releaseDate
            }
            Glide.with(itemView)
                .load(Constants.IMAGE_URL + movie.posterPath)
                .into(binding.movieImage)
        }
    }
}