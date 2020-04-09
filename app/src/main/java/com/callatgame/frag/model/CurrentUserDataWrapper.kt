package com.callatgame.frag.model

import com.google.gson.annotations.SerializedName

data class CurrentUserDataWrapper(@SerializedName("user") val user : User, @SerializedName("player") val player : Player) {
}