package com.tugcearas.movieapp.data.model.login

import com.google.gson.annotations.SerializedName

data class ValidationRequestModel(

    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("request_token")
    val requestToken: String
)
