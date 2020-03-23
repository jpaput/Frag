package com.callatgame.frag.main.task

import android.content.Context
import com.callatgame.frag.core.AbstractTask
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.service.TechnicalService

class Test401Task(val context: Context) : AbstractTask<DefaultResponse>(context) {

    override fun doCall(): DefaultResponse {
        return TechnicalService(mContext).test401()
    }
}