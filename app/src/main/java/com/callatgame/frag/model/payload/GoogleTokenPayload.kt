package com.callatgame.frag.model.payload

import com.google.gson.annotations.SerializedName

data class GoogleTokenPayload(@SerializedName("token") val token : String)