package com.tugcearas.movieapp.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tugcearas.movieapp.data.model.movie.FavoriteModel
import com.tugcearas.movieapp.databinding.FavoriteItemBinding
import com.tugcearas.movieapp.util.diffutil.DiffUtilCallback

class FavoriteAdapter:
ListAdapter<FavoriteModel, FavoriteAdapter.FavoriteViewHolder>(
    DiffUtilCallback<FavoriteModel>(
        itemsTheSame = { oldItem, newItem ->
            oldItem == newItem
        },
        contentsTheSame = { oldItem, newItem ->
            oldItem == newItem
        }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavoriteViewHolder(
        FavoriteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class FavoriteViewHolder(val binding:FavoriteItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie:FavoriteModel){
            binding.movie = movie
            binding.executePendingBindings()
        }
    }
}
