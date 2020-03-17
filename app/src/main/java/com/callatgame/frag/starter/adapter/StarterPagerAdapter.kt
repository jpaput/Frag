package com.callatgame.frag.starter.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.callatgame.frag.starter.LoginFragment
import com.callatgame.frag.starter.SignUpFragment

class StarterPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private val fragmentList: List<Fragment> = createFragmentList()

    private fun createFragmentList() : List<Fragment>{
        return listOf(
            LoginFragment.newInstance(),
            SignUpFragment.newInstance())
    }

    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment = fragmentList[position]
}