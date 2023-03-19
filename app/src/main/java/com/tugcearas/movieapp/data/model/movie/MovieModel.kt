package com.tugcearas.movieapp.data.model.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val results: List<MovieModel>,
) : Parcelable

@Parcelize
data class MovieModel(
    val id: Int,
    val page: Int,
    val overview: String,
    val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double
) : Parcelable