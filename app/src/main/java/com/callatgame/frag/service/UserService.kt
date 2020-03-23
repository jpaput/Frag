package com.callatgame.frag.service

import android.content.Context
import com.callatgame.frag.core.AbstractService
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.TokenResponse
import com.callatgame.frag.model.User
import com.callatgame.frag.model.payload.LoginPayload
import com.callatgame.frag.model.payload.SignupPayload
import com.callatgame.frag.model.payload.VerifyEmailPayload

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

    fun getUser() : User {
        return execute(
            apiEndPoint.getUser())!!
    }
}