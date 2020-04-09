package com.callatgame.frag.starter.task

import android.content.Context
import com.callatgame.frag.core.AbstractTask
import com.callatgame.frag.core.PreferenceManager
import com.callatgame.frag.core.SessionManager
import com.callatgame.frag.model.AuthentificationMethod
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.payload.GoogleTokenPayload
import com.callatgame.frag.service.UserService

class SendGoogleTokenTask(val context: Context, val payload: GoogleTokenPayload) : AbstractTask<DefaultResponse>(context) {

    override fun doCall(): DefaultResponse {
        val result = UserService(context).sendGoogleToken(payload)

        PreferenceManager(context).saveToken(result.token)
        PreferenceManager(context).setAuthentificationMethod(AuthentificationMethod.GOOGLE)

        UserService(context).getUserData()

        return DefaultResponse("")
    }
}