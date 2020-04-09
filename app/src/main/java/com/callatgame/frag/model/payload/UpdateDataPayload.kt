package com.callatgame.frag.model.payload

import com.google.gson.annotations.SerializedName

data class UpdateDataPayload(@SerializedName("field") val field : String, @SerializedName("value") val value : String)