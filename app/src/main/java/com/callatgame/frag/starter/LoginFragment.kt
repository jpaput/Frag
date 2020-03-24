package com.callatgame.frag.starter

import android.os.Bundle
import android.util.Patterns
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
import com.callatgame.frag.utils.VibratorUtils
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : AbstractFragment() {

    private val ERROR_INCORRECT_EMAIL = 1
    private val ERROR_EMPTY_EMAIL = 3
    private val ERROR_EMPTY_PASSWORD = 4
    private val NO_ERROR = 0

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
            checkFields()
        }

        view.findViewById<View>(R.id.create_account_text_button).setOnClickListener {
            (activity as StarterActivity?)!!.changeToPage(StarterActivity.SIGN_UP)
        }

        return view;
    }

    private fun makePayload(): LoginPayload {
        return LoginPayload(
            email_edittext.text.toString(),
            password_edittext.toString()
        )
    }

    fun checkFields() {
        clearHighlightErrors()
        val error = getErrorCode()
        if (error == NO_ERROR) {
            login()
        } else {
            highlightError(error)
        }
    }

    private fun clearHighlightErrors() {
        email_edittext.setError(null)
        password_edittext.setError(null)
    }

    private fun highlightError(errorCode: Int) {
        VibratorUtils(context!!).vibrate(VibratorUtils.SHORT_DURATION)
        when (errorCode) {
            ERROR_EMPTY_EMAIL -> email_textinputlayout.setError(
                getString(R.string.mandatory_field_message))

            ERROR_EMPTY_PASSWORD -> password_textinputlayout.setError(
                getString(R.string.mandatory_field_message))

            ERROR_INCORRECT_EMAIL -> email_textinputlayout.setError(
                getString(R.string.email_incorrect_format_message))
        }
    }


    private fun getErrorCode(): Int {
        if (email_edittext.getText()!!.length == 0) {
            return ERROR_EMPTY_EMAIL
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email_edittext.getText().toString()).matches()) {
            return ERROR_INCORRECT_EMAIL
        }
        if (password_edittext.getText()!!.length == 0) {
            return ERROR_EMPTY_PASSWORD
        } else {
            return NO_ERROR
        }
    }

    private fun login(){
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
}