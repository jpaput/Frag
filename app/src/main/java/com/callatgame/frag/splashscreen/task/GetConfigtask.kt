package com.callatgame.frag.splashscreen.task

import android.content.Context
import com.callatgame.frag.core.AbstractTask
import com.callatgame.frag.core.PreferenceManager
import com.callatgame.frag.core.SessionManager
import com.callatgame.frag.model.Config
import com.callatgame.frag.service.TechnicalService
import com.callatgame.frag.service.UserService

class GetConfigtask(val context: Context) : AbstractTask<Config>(context) {

    override fun doCall(): Config {
        val config = TechnicalService(context).getConfig()

        if(PreferenceManager(context).hasToken()){
            SessionManager.instance.user = UserService(context).getUser()
        }

        return config;
    }
}