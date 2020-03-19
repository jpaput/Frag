package com.callatgame.frag.starter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.callatgame.frag.R
import com.callatgame.frag.core.AbstractActivity
import com.callatgame.frag.core.AbstractFragment
import com.callatgame.frag.core.RequestCallBack
import com.callatgame.frag.model.CaGError
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.payload.SignupPayload
import com.callatgame.frag.service.UserService
import kotlinx.android.synthetic.main.sign_up_fragment.*

class SignUpFragment : AbstractFragment() {

    companion object {
        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(
            R.layout.sign_up_fragment,
            container,
            false
        )

        view.findViewById<Button>(R.id.signupButton).setOnClickListener {
            showProgressDialog()
            UserService(activity!!.baseContext).signup(
                makePayload(),
                object : RequestCallBack<DefaultResponse> {

                    override fun onSuccess(result: DefaultResponse) {
                        hideProgressDialog()
                        getAbstractActivity().addFragmentToActivity(
                            EmailSentFragment(),
                            android.R.id.content
                        )
                    }

                    override fun onError(error: CaGError) {
                        hideProgressDialog()
                        showError(error.message)
                    }
                })
        }

        return view
    }


    private fun makePayload(): SignupPayload {
        return SignupPayload(
            emailView.text.toString(),
            passView.text.toString()
        )
    }
}