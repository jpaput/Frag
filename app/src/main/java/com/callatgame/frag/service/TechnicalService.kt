package com.callatgame.frag.service

import android.content.Context
import androidx.annotation.WorkerThread
import com.callatgame.frag.core.AbstractService
import com.callatgame.frag.core.RequestCallBack
import com.callatgame.frag.model.Config
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TechnicalService(context : Context) : AbstractService(context) {

    @WorkerThread
    fun getConfig(callback: RequestCallBack<Config>) {
        CoroutineScope(Dispatchers.IO).launch {
            execute(
                apiEndPoint.getConfig(),
                callback)
        }
    }
}