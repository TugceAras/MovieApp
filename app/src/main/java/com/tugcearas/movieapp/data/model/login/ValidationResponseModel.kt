package com.tugcearas.movieapp.data.model.login

import com.google.gson.annotations.SerializedName

data class ValidationResponseModel(

    @SerializedName("expires_at")
    val expiresAt: String,

    @SerializedName("request_token")
    val requestToken: String,

    val success: Boolean
)