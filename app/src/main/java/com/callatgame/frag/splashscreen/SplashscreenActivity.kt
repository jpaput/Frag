package com.callatgame.frag.splashscreen

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import com.callatgame.frag.R
import com.callatgame.frag.common.exception.CaGException
import com.callatgame.frag.core.AbstractActivity
import com.callatgame.frag.core.PreferenceManager
import com.callatgame.frag.core.RequestCallBack
import com.callatgame.frag.core.SessionManager
import com.callatgame.frag.main.MainActivity
import com.callatgame.frag.model.AuthentificationMethod
import com.callatgame.frag.model.Config
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.payload.GoogleTokenPayload
import com.callatgame.frag.service.UserService
import com.callatgame.frag.splashscreen.task.GetConfigtask
import com.callatgame.frag.splashscreen.task.RetreiveAuthTask
import com.callatgame.frag.starter.StarterActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashscreenActivity : AbstractActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen_activity)

        showAppVersion()
    }

    override fun onResume() {
        super.onResume()

        GlobalScope.launch(context = Dispatchers.Main) {
            delay(1200)
            getConfig()
        }
    }

    private fun showAppVersion() {
        try {
            val version =
                packageManager.getPackageInfo(packageName, 0).versionName
            val versionText: String = getString(R.string.version_string).plus(version)
            findViewById<TextView>(R.id.app_version_textview).setText(versionText)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun getConfig() {

        GetConfigtask(baseContext).execute(
            object : RequestCallBack<Config> {
                override fun onSuccess(result: Config) {
                    if(PreferenceManager(baseContext).getAuthentificationMethod() == AuthentificationMethod.NONE){
                        startActivity(StarterActivity.newIntent(baseContext))
                        finishAffinity()
                    }else{
                        retreiveAuth()
                    }
                }

                override fun onError(error: CaGException) {
                    showError(error.message)
                }
            }
        )
    }

    private fun retreiveAuth(){
        RetreiveAuthTask(baseContext).execute(
            object : RequestCallBack<DefaultResponse> {
                override fun onSuccess(result: DefaultResponse) {
                    startActivity(MainActivity.newIntent(baseContext))
                    finishAffinity()
                }

                override fun onError(error: CaGException) {
                    startActivity(StarterActivity.newIntent(baseContext))
                    finishAffinity()
                }
            }
        )
    }
}
