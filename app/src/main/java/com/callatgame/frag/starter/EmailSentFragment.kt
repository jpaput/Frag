package com.callatgame.frag.starter

import android.content.Intent
import android.content.pm.LabeledIntent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.callatgame.frag.R
import com.callatgame.frag.core.AbstractFragment
import java.util.*

class EmailSentFragment : AbstractFragment() {

    private val PAYPAL = "com.paypal.android.p2pmobile"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.emailsent_fragment, container, false)

        view.findViewById<Button>(R.id.open_client_button).setOnClickListener {

            val emailIntent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"))

            try {
                val pm: PackageManager = activity!!.getPackageManager()
                val resInfo = pm.queryIntentActivities(emailIntent, 0)
                if (resInfo.size > 0) {

                    var ri = resInfo[0]
                    val intentChooser = pm.getLaunchIntentForPackage(ri.activityInfo.packageName)
                    val openInChooser = Intent.createChooser(intentChooser, getString(R.string.choose_email))
                    val intentList: MutableList<LabeledIntent> = ArrayList()

                    for (i in 1 until resInfo.size) {
                        ri = resInfo[i]
                        val packageName = ri.activityInfo.packageName
                        val intent = pm.getLaunchIntentForPackage(packageName)
                        if (PAYPAL != packageName) {
                            intentList.add(
                                LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon)
                            )
                        }
                    }

                    val extraIntents =
                        intentList.toTypedArray()
                    openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents)
                    startActivity(openInChooser)
                }
            } catch (exception: Exception) {
                Log.e(EmailSentFragment::class.java.simpleName, exception.toString())
            }
        }

        return view
    }
}