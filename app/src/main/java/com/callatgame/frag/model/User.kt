package com.callatgame.frag.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_id") val userId : Int,
    @SerializedName("email") val email : String,
    @SerializedName("gender") val gender : String,
    @SerializedName("firstname") val firstName : String,
    @SerializedName("lastname") val  lastName : String,
    @SerializedName("dob") val dateOfBirth : String,
    @SerializedName("player_id") val playerId : Int

) : Data