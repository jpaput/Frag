package com.callatgame.frag.splashscreen.task

import android.content.Context
import com.callatgame.frag.core.AbstractTask
import com.callatgame.frag.core.PreferenceManager
import com.callatgame.frag.core.SessionManager
import com.callatgame.frag.model.AuthentificationMethod
import com.callatgame.frag.model.Config
import com.callatgame.frag.model.payload.GoogleTokenPayload
import com.callatgame.frag.service.TechnicalService
import com.callatgame.frag.service.UserService
import com.google.android.gms.auth.api.signin.GoogleSignIn

class GetConfigtask(val context: Context) : AbstractTask<Config>(context) {

    override fun doCall(): Config {

        val config = TechnicalService(context).getConfig()

        when(PreferenceManager(context).getAuthentificationMethod()){
            AuthentificationMethod.CREDENTIALS -> {
                SessionManager.instance.user = UserService(context).getUser()
            }
            AuthentificationMethod.GOOGLE -> {
                val account = GoogleSignIn.getLastSignedInAccount(context)
                val result = UserService(context).sendGoogleToken(GoogleTokenPayload(account!!.idToken!!))
                PreferenceManager(context).saveToken(result.token)

                SessionManager.instance.user = UserService(context).getUser()
            }
        }

        return config;
    }
}