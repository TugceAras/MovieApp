package com.tugcearas.movieapp.data.model.login

import com.google.gson.annotations.SerializedName

data class CreateTokenModel(

    @SerializedName("expires_at")
    val expirestAt: String,

    @SerializedName("request_token")
    val requestToken: String,

    val success: Boolean
)