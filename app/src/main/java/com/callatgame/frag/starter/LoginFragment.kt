package com.callatgame.frag.starter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.callatgame.frag.R
import com.callatgame.frag.core.AbstractFragment
import com.callatgame.frag.core.PreferenceManager
import com.callatgame.frag.core.RequestCallBack
import com.callatgame.frag.model.CaGError
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.TokenResponse
import com.callatgame.frag.model.payload.LoginPayload
import com.callatgame.frag.model.payload.SignupPayload
import com.callatgame.frag.service.UserService
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : AbstractFragment() {

    companion object{
        fun newInstance() : LoginFragment{
            return LoginFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.login_fragment, container, false)

            view.findViewById<Button>(R.id.loginButton).setOnClickListener{
            showProgressDialog()
            UserService(activity!!.baseContext).login(
                makePayload(),
                object : RequestCallBack<TokenResponse> {

                    override fun onSuccess(result: TokenResponse) {
                        hideProgressDialog()
                        PreferenceManager(context).saveToken(result.token)
                        showSuccessDialog()
                    }

                    override fun onError(error: CaGError) {
                        hideProgressDialog()
                        showError(error.message)
                    }
                })
        }

        return view;
    }

    private fun makePayload(): LoginPayload {
        return LoginPayload(
            emailView.text.toString(),
            passView.text.toString())
    }
}