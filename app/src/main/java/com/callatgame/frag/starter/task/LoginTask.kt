package com.callatgame.frag.starter.task

import android.content.Context
import com.callatgame.frag.core.AbstractTask
import com.callatgame.frag.core.PreferenceManager
import com.callatgame.frag.core.SessionManager
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.payload.LoginPayload
import com.callatgame.frag.service.UserService

class LoginTask(val context: Context, val payload: LoginPayload) : AbstractTask<DefaultResponse>(context) {

    override fun doCall(): DefaultResponse {
        val result = UserService(context).login(payload)
        PreferenceManager(context).saveToken(result.token)

        SessionManager.instance.user = UserService(context).getUser()

        return DefaultResponse("")
    }
}