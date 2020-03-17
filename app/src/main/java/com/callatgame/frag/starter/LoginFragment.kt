package com.callatgame.frag.starter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.callatgame.frag.R

class LoginFragment : Fragment() {

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

        val view = inflater.inflate(R.layout.login_fragment, container, false); //Contains empty RelativeLayout
        return view;
    }
}