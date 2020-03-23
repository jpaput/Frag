package com.callatgame.frag.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.callatgame.frag.R
import com.callatgame.frag.common.exception.CaGException
import com.callatgame.frag.core.AbstractActivity
import com.callatgame.frag.core.RequestCallBack
import com.callatgame.frag.main.task.Test401Task
import com.callatgame.frag.model.DefaultResponse
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AbstractActivity() {

    companion object{

        fun newIntent(context : Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        test_button.setOnClickListener{
            Test401Task(baseContext).execute(
                object : RequestCallBack<DefaultResponse> {
                    override fun onSuccess(result: DefaultResponse) {
                    }

                    override fun onError(error: CaGException) {
                        //progress_wheel.hide()
                        showError(error.message)
                    }
                }
            )
        }
    }

}