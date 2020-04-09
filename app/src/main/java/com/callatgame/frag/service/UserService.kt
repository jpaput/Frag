package com.callatgame.frag.service

import android.content.Context
import com.callatgame.frag.core.AbstractService
import com.callatgame.frag.core.SessionManager
import com.callatgame.frag.model.CurrentUserDataWrapper
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.TokenResponse
import com.callatgame.frag.model.User
import com.callatgame.frag.model.payload.*

class UserService(context: Context) : AbstractService(context) {

    fun signup(payload: SignupPayload): DefaultResponse {
        return execute(
            apiEndPoint.signup(payload))!!
    }

    fun login(payload: LoginPayload): TokenResponse {
        return execute(
            apiEndPoint.login(payload))!!
    }

    fun verify(payload: VerifyEmailPayload): DefaultResponse {
        return execute(
            apiEndPoint.verify(payload))!!
    }

    fun getUserData() {

        val wrapper = execute(
            apiEndPoint.getUserData())!!

        SessionManager.instance.user = wrapper.user
        SessionManager.instance.player = wrapper.player
    }

    fun sendGoogleToken(payload: GoogleTokenPayload) : TokenResponse {
        return execute(
            apiEndPoint.sendGoogleToken(payload))!!
    }

    fun updateUserData(payload : UpdateDataPayload) : DefaultResponse{
        return execute(
            apiEndPoint.updateUserData(payload))!!
    }
}