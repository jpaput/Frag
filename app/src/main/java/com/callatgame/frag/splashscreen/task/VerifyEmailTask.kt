package com.callatgame.frag.splashscreen.task

import android.content.Context
import com.callatgame.frag.core.AbstractTask
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.payload.VerifyEmailPayload
import com.callatgame.frag.service.UserService

class VerifyEmailTask(val context: Context,val token : String) : AbstractTask<DefaultResponse>(context) {


    override fun doCall(): DefaultResponse {
        return UserService(context).verify(VerifyEmailPayload(token));
    }
}