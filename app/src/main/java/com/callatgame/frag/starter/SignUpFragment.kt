package com.callatgame.frag.starter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.callatgame.frag.R

class SignUpFragment : Fragment() {

    companion object{
        fun newInstance() : SignUpFragment{
            return SignUpFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.sign_up_fragment, container, false); //Contains empty RelativeLayout
        return view;
    }
}