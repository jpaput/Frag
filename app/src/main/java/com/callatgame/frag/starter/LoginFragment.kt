package com.callatgame.frag.starter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.callatgame.frag.R
import com.callatgame.frag.common.exception.CaGException
import com.callatgame.frag.core.AbstractFragment
import com.callatgame.frag.core.RequestCallBack
import com.callatgame.frag.main.MainActivity
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.payload.GoogleTokenPayload
import com.callatgame.frag.model.payload.LoginPayload
import com.callatgame.frag.starter.task.LoginTask
import com.callatgame.frag.starter.task.SendGoogleTokenTask
import com.callatgame.frag.utils.VibratorUtils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : AbstractFragment() {

    private val GOOGLE_SIGN_IN_REQUEST_CODE = 101

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

        view.findViewById<View>(R.id.googleButton)!!.setOnClickListener {
            googleSignIn()

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

    private fun googleSignIn() {

        startActivityForResult(
            GoogleSignIn.getClient(
                activity!!,
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build())
                .getSignInIntent(),
            GOOGLE_SIGN_IN_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) when (requestCode) {
            GOOGLE_SIGN_IN_REQUEST_CODE -> try {

                sendGoogleToken(
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                        .getResult(ApiException::class.java)!!
                            .idToken!!)

            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
                Log.w(LoginFragment::class.java.simpleName, "signInResult:failed code=" + e.statusCode)
            }
        }
    }

    private fun sendGoogleToken(idToken: String) {

        Log.d(LoginFragment::class.java.simpleName, "Google token  =" + idToken)

        showProgressDialog()
        SendGoogleTokenTask(context!!, GoogleTokenPayload(idToken)).execute(
            object : RequestCallBack<DefaultResponse> {

                override fun onSuccess(result: DefaultResponse) {
                    hideProgressDialog()
                    startActivity(MainActivity.newIntent(activity!!))
                }

                override fun onError(error: CaGException) {
                    hideProgressDialog()
                    showError(error.message!!)
                }
            }
        )
    }
}