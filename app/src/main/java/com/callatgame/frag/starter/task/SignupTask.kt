package com.callatgame.frag.starter.task

import android.content.Context
import com.callatgame.frag.core.AbstractTask
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.payload.SignupPayload
import com.callatgame.frag.service.UserService

class SignupTask(val context: Context,val payload: SignupPayload) : AbstractTask<DefaultResponse>(context) {


    override fun doCall(): DefaultResponse {
        return UserService(context).signup(payload)
    }
}