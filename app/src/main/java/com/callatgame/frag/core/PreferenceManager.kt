package com.callatgame.frag.core

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(private val context: Context?) {


    fun getDefaultSharedPreferences(context: Context?): SharedPreferences {
        return context!!.getSharedPreferences(
            context.packageName + "_preferences",
            Context.MODE_PRIVATE
        )
    }

}