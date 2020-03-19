package com.callatgame.frag.model.payload

import com.google.gson.annotations.SerializedName

data class VerifyEmailPayload(
    @SerializedName("token")  val token : String
)
