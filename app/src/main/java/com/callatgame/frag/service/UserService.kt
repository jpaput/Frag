package com.callatgame.frag.service

import android.content.Context
import androidx.annotation.WorkerThread
import com.callatgame.frag.core.AbstractService
import com.callatgame.frag.core.RequestCallBack
import com.callatgame.frag.model.Config
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.TokenResponse
import com.callatgame.frag.model.payload.LoginPayload
import com.callatgame.frag.model.payload.SignupPayload
import com.callatgame.frag.model.payload.VerifyEmailPayload
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserService(context : Context) : AbstractService(context) {

    @WorkerThread
    fun signup(payload : SignupPayload, callback: RequestCallBack<DefaultResponse>) {
        CoroutineScope(Dispatchers.IO).launch {
            execute(
                apiEndPoint.signup(payload),
                callback)
        }
    }

    @WorkerThread
    fun login(payload : LoginPayload, callback: RequestCallBack<TokenResponse>) {
        CoroutineScope(Dispatchers.IO).launch {
            execute(
                apiEndPoint.login(payload),
                callback)
        }
    }

    fun verify(payload: VerifyEmailPayload, callBack: RequestCallBack<DefaultResponse>) {
        CoroutineScope(Dispatchers.IO).launch {
            execute(
                apiEndPoint.verify(payload),
                callBack)
        }
    }
}