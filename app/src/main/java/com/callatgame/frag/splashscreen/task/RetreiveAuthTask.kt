package com.callatgame.frag.splashscreen.task

import android.content.Context
import com.callatgame.frag.core.AbstractTask
import com.callatgame.frag.core.PreferenceManager
import com.callatgame.frag.core.SessionManager
import com.callatgame.frag.model.AuthentificationMethod
import com.callatgame.frag.model.Config
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.payload.GoogleTokenPayload
import com.callatgame.frag.service.TechnicalService
import com.callatgame.frag.service.UserService
import com.google.android.gms.auth.api.signin.GoogleSignIn

class RetreiveAuthTask(val context: Context) : AbstractTask<DefaultResponse>(context) {

    override fun doCall(): DefaultResponse {

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
            AuthentificationMethod.FACEBOOK -> {/*TODO*/}
            else -> {/*TODO*/}
        }
        return DefaultResponse("");
    }
}