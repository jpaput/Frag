package com.callatgame.frag.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.callatgame.frag.R
import com.callatgame.frag.core.AbstractFragment

class GamesFragment : AbstractFragment() {

    companion object{

        fun newInstance() : Fragment {
            return GamesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.games_fragment, container, false)

        return view
    }
}