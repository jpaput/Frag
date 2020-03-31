package com.callatgame.frag.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.callatgame.frag.R
import com.callatgame.frag.core.AbstractActivity
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

        activity_main_bottom_navigation.setOnNavigationItemSelectedListener({ item -> updateMainFragment(item.getItemId()) })

        activity_main_bottom_navigation.selectedItemId = R.id.profile_item

        /*test_button.setOnClickListener{
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
        }*/
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{replace(frameId, fragment)}
    }

    private fun updateMainFragment(itemId: Int): Boolean {
        val fragment : Fragment

        when(itemId){
            R.id.profile_item -> {
                fragment = ProfileFragment.newInstance()
            }
            R.id.rank_item -> {
                fragment = RankFragment.newInstance()
            }
            R.id.games_item -> {
                fragment = GamesFragment.newInstance()
            }
            else -> {
                fragment = ProfileFragment.newInstance()
            }
        }
        replaceFragment(fragment, R.id.container)

        return true
    }
}