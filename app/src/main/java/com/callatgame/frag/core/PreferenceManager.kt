package com.callatgame.frag.core

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.callatgame.frag.model.AuthentificationMethod

class PreferenceManager(private val context: Context?) {

    internal enum class Keys(val key: String) {
        KEY_TOKEN("KEY_TOKEN"),
        KEY_AUTHENTIFICATION_METHOD("KEY_AUTHENTIFICATION_METHOD")
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

    fun setAuthentificationMethod(method : AuthentificationMethod) {
        return getDefaultSharedPreferences(context)
            .edit().putString(Keys.KEY_AUTHENTIFICATION_METHOD.key, method.name)
            .apply()
    }

    fun getAuthentificationMethod() : AuthentificationMethod {
        return AuthentificationMethod.valueOf(getDefaultSharedPreferences(context).getString(
            Keys.KEY_AUTHENTIFICATION_METHOD.key, "NONE").toString())
    }

}