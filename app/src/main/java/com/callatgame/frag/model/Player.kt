package com.callatgame.frag.model

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("profile_pic") val profilPic : String,
    @SerializedName("playername") val playername : String,
    @SerializedName("weight") val weight : String,
    @SerializedName("size") val size : String,
    @SerializedName("attack") val attack : String,
    @SerializedName("defense") val defense : String,
    @SerializedName("speed") val speed : String,
    @SerializedName("stamina") val stamina : String,
    @SerializedName("technic") val technic : String,
    @SerializedName("aim") val aim : String
)
