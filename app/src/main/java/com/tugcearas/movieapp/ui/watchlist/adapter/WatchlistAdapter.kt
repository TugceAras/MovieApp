package com.tugcearas.movieapp.ui.watchlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tugcearas.movieapp.data.model.movie.WatchlistModel
import com.tugcearas.movieapp.databinding.WatchlistItemBinding
import com.tugcearas.movieapp.util.diffutil.DiffUtilCallback

class WatchlistAdapter :
ListAdapter<WatchlistModel, WatchlistAdapter.WatchlistViewHolder>(
    DiffUtilCallback<WatchlistModel>(
        itemsTheSame = { oldItem, newItem ->
            oldItem == newItem
        },
        contentsTheSame = { oldItem, newItem ->
            oldItem == newItem
        }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WatchlistViewHolder(
        WatchlistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: WatchlistViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class WatchlistViewHolder(val binding: WatchlistItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(watchlistMovie:WatchlistModel) = with(binding){
            movie = watchlistMovie
            executePendingBindings()
        }
    }
}
