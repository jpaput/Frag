package com.callatgame.frag.starter

import android.content.Context
import android.content.Intent
import com.callatgame.frag.core.AbstractActivity

class StarterActivity : AbstractActivity() {

    companion object{

        fun newIntent(context : Context) : Intent{
            return Intent(context, StarterActivity::class.java)
        }
    }
}