package com.callatgame.frag.core

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(private val context: Context?) {

    internal enum class Keys(val key: String) {
        KEY_TOKEN("KEY_TOKEN")
    }

    fun getDefaultSharedPreferences(context: Context?): SharedPreferences {
        return context!!.getSharedPreferences(
            context.packageName + "_preferences",
            Context.MODE_PRIVATE
        )
    }

    fun saveToken(token: String) {
       getDefaultSharedPreferences(context)
            .edit().putString(Keys.KEY_TOKEN.key, token)
            .apply()
    }

    fun getToken(): String {
        return getDefaultSharedPreferences(context).getString(
            Keys.KEY_TOKEN.key, "")
            .toString()

    }

    fun hasToken(): Boolean {
        return !getDefaultSharedPreferences(context)
            .getString(Keys.KEY_TOKEN.key, "")!!
            .isEmpty()

    }

}