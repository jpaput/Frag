package com.callatgame.frag.splashscreen

import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.TextView
import com.callatgame.frag.R
import com.callatgame.frag.core.AbstractActivity
import com.callatgame.frag.core.RequestCallBack
import com.callatgame.frag.model.CaGError
import com.callatgame.frag.model.Config
import com.callatgame.frag.model.DeeplinkType
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.payload.VerifyEmailPayload
import com.callatgame.frag.service.TechnicalService
import com.callatgame.frag.service.UserService
import com.callatgame.frag.starter.StarterActivity
import com.callatgame.frag.starter.StarterActivity.Companion.EMAIL_CONFIRMED
import kotlinx.android.synthetic.main.progress_dialog.*


class DeeplinkActivity :AbstractActivity() {

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
        TechnicalService(baseContext).getConfig(
            object : RequestCallBack<Config> {

                override fun onSuccess(response: Config) {
                    manageDeeplink()
                }

                override fun onError(error: CaGError) {
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

    private fun verifyEmail(token : String) {
        UserService(baseContext).verify(
            VerifyEmailPayload(token),
            object : RequestCallBack<DefaultResponse> {

                override fun onSuccess(result: DefaultResponse) {
                    hideProgressDialog()
                    startActivity(
                        StarterActivity.newIntent(baseContext)
                            .putExtra(EMAIL_CONFIRMED, true))
                    finishAffinity()
                }

                override fun onError(error: CaGError) {
                    showError(error.message)
                    hideProgressDialog()
                }
            })
    }
}