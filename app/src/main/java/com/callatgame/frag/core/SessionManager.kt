package com.callatgame.frag.core

import com.callatgame.frag.model.User

class SessionManager private constructor() {


    lateinit var user: User

    private object HOLDER {
        val INSTANCE = SessionManager()
    }

    companion object {
        val instance: SessionManager by lazy { HOLDER.INSTANCE }
    }


}