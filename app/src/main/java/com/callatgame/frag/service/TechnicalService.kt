package com.callatgame.frag.service

import android.content.Context
import androidx.annotation.WorkerThread
import com.callatgame.frag.core.AbstractService
import com.callatgame.frag.model.Config
import com.callatgame.frag.model.DefaultResponse

class TechnicalService(context : Context) : AbstractService(context) {

    @WorkerThread
    fun getConfig() : Config {
        return execute(apiEndPoint.getConfig())!!
    }

    fun test401(): DefaultResponse {
        return execute(apiEndPoint.test401())!!
    }
}