package com.callatgame.frag.core

import com.callatgame.frag.model.*
import com.callatgame.frag.model.payload.*
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    companion object {
        const val API = "/api"
        const val V1 = "/v1"

        //Technical
        const val TECHNICAL = "/technical"
        const val CONFIG = "/config"

        //Users
        const val USERS = "/users"
        const val AUTH = "/auth"
        const val VERIFY = "/verify"
        const val ME = "/me"
        const val GOOGLE = "/googleTokenSignin"

        //Players
        const val PLAYER = "/players"

    }

    @GET(API + V1 + TECHNICAL + CONFIG)
    fun getConfig() : Call<Config>

    //User register
    @POST(API + V1 + USERS)
    fun signup(@Body payload: SignupPayload): Call<DefaultResponse>

    //User Authentification
    @POST(API + V1 + USERS + AUTH)
    fun login(@Body payload: LoginPayload): Call<TokenResponse>

    //Verify user email
    @POST(API + V1 + USERS + VERIFY)
    fun verify(@Body payload: VerifyEmailPayload): Call<DefaultResponse>

    //Get current user data
    @GET(API + V1 + USERS + ME)
    fun getUserData(): Call<CurrentUserDataWrapper>

    //Send Google token after Google SignIn
    @POST(API + V1 + USERS + GOOGLE)
    fun sendGoogleToken(@Body payload: GoogleTokenPayload): Call<TokenResponse>

    @GET(API + V1 + TECHNICAL + "/401")
    fun test401(): Call<DefaultResponse?>

    //Get playerData
    @GET(API + V1 + PLAYER)
    fun getPlayerData(@Query("playerId") playerId: Int): Call<Player>

    // Update user data
    @PUT(API + V1 + USERS)
    fun updateUserData(@Body  payload: UpdateDataPayload): Call<DefaultResponse>

    // Update player data
    @PUT(API + V1 + PLAYER)
    fun updatePlayerData(@Body  payload: UpdateDataPayload): Call<DefaultResponse>
}