package com.callatgame.frag.splashscreen

import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import com.callatgame.frag.R
import com.callatgame.frag.common.exception.CaGException
import com.callatgame.frag.core.AbstractActivity
import com.callatgame.frag.core.RequestCallBack
import com.callatgame.frag.model.Config
import com.callatgame.frag.model.DeeplinkType
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.splashscreen.task.GetConfigtask
import com.callatgame.frag.splashscreen.task.VerifyEmailTask
import com.callatgame.frag.starter.StarterActivity
import com.callatgame.frag.starter.StarterActivity.Companion.EMAIL_CONFIRMED


class DeeplinkActivity : AbstractActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen_activity)

        showAppVersion()
    }

    override fun onResume() {
        super.onResume()

        getConfig()
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

        showProgressDialog()

        GetConfigtask(baseContext).execute(
            object : RequestCallBack<Config> {
                override fun onSuccess(result: Config) {
                    manageDeeplink()
                }

                override fun onError(error: CaGException) {
                    hideProgressDialog()
                    showError(error.message)
                }
            }
        )
    }

    private fun manageDeeplink() {
        if (intent != null) {
            val deeplink = intent.data
            if (deeplink != null && !TextUtils.isEmpty(deeplink.toString())
                && deeplink.path != null
            ) {
                val segments = deeplink.path!!.split("/").toTypedArray()
                val deeplinkType: DeeplinkType = DeeplinkType.valueOf(segments[1])
                val deeplinkValue = segments[2]

                when (deeplinkType) {
                    DeeplinkType.verify -> verifyEmail(deeplinkValue)
                }
            }
        }
    }

    private fun verifyEmail(token: String) {

        VerifyEmailTask(baseContext, token).execute(
            object : RequestCallBack<DefaultResponse> {

                override fun onSuccess(result: DefaultResponse) {
                    hideProgressDialog()
                    startActivity(
                        StarterActivity.newIntent(baseContext)
                            .putExtra(EMAIL_CONFIRMED, true)
                    )
                    finishAffinity()
                }

                override fun onError(error: CaGException) {
                    showError(error.message)
                    hideProgressDialog()
                }
            })

    }
}