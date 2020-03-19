package com.callatgame.frag.core

import com.callatgame.frag.model.Config
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.payload.LoginPayload
import com.callatgame.frag.model.payload.SignupPayload
import com.callatgame.frag.model.payload.VerifyEmailPayload
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiEndPoint {

    companion object {
        const val API = "/api"
        const val V1 = "/v1"

        const val TECHNICAL = "/technical"
        const val CONFIG = "/config"

        const val USERS = "/users"
        const val AUTH = "/auth"
        const val VERIFY = "/verify"

    }

    @GET(API + V1 + TECHNICAL + CONFIG)
    fun getConfig() : Call<Config>

    @POST(API + V1 + USERS)
    fun signup(@Body payload: SignupPayload): Call<DefaultResponse>

    @POST(API + V1 + USERS + AUTH)
    fun login(@Body payload: LoginPayload): Call<DefaultResponse>

    @POST(API + V1 + USERS + VERIFY)
    fun verify(@Body payload: VerifyEmailPayload): Call<DefaultResponse>
}