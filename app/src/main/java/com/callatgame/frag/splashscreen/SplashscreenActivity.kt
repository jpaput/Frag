package com.callatgame.frag.splashscreen

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import com.callatgame.frag.R
import com.callatgame.frag.common.callback.LoaderCallback
import com.callatgame.frag.core.AbstractActivity
import com.callatgame.frag.core.RequestCallBack
import com.callatgame.frag.model.CaGError
import com.callatgame.frag.model.Config
import com.callatgame.frag.service.TechnicalService
import com.callatgame.frag.starter.StarterActivity
import kotlinx.android.synthetic.main.splashscreen_activity.*
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


        TechnicalService(baseContext).getConfig(
            object : RequestCallBack<Config> {

                override fun onSuccess(response: Config) {
                    startActivity(StarterActivity.newIntent(baseContext))
                              finishAffinity()
                   
                }

                override fun onError(error: CaGError) {
                    //progress_wheel.hide()
                    showError(error.message)
                }
            }
        )
    }
}
