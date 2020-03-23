package com.callatgame.frag.starter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.callatgame.frag.R
import com.callatgame.frag.common.exception.CaGException
import com.callatgame.frag.core.AbstractFragment
import com.callatgame.frag.core.RequestCallBack
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.payload.LoginPayload
import com.callatgame.frag.starter.task.LoginTask
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : AbstractFragment() {

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.login_fragment, container, false)

        view.findViewById<Button>(R.id.loginButton).setOnClickListener {
            showProgressDialog()
            LoginTask(context!!, makePayload()).execute(
                object : RequestCallBack<DefaultResponse> {

                    override fun onSuccess(result: DefaultResponse) {
                        hideProgressDialog()
                        showSuccessDialog()
                    }

                    override fun onError(error: CaGException) {
                        hideProgressDialog()
                        showError(error.message!!)
                    }
                })

        }

        return view;
    }

    private fun makePayload(): LoginPayload {
        return LoginPayload(
            emailView.text.toString(),
            passView.text.toString()
        )
    }
}