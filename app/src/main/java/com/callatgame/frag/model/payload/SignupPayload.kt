package com.callatgame.frag.model.payload

import com.google.gson.annotations.SerializedName

data class SignupPayload(
    @SerializedName("email")  val email : String,
    @SerializedName("password")  val password : String
)
