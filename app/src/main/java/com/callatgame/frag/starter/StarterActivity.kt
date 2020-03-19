package com.callatgame.frag.starter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.callatgame.frag.R
import com.callatgame.frag.core.AbstractActivity
import com.callatgame.frag.starter.adapter.StarterPagerAdapter
import kotlinx.android.synthetic.main.start_activity.*

class StarterActivity : AbstractActivity(), ViewPager.PageTransformer,
    ViewPager.OnPageChangeListener {

    companion object{

        val EMAIL_CONFIRMED = "EMAIL_CONFIRMED"

        fun newIntent(context : Context) : Intent{
            return Intent(context, StarterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.start_activity)

        if (intent != null) {
            if (intent.hasExtra(EMAIL_CONFIRMED)) {
                showSuccessDialog(getString(R.string.email_confirmed))
                intent.removeExtra(EMAIL_CONFIRMED)
            }
        }

        viewpager.adapter = StarterPagerAdapter(supportFragmentManager);
        viewpager.setPageTransformer(false, this)
        viewpager.addOnPageChangeListener(this)


    }

    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.width
        when {
            position < -1 -> view.alpha = 0f
            position <= 1 -> {
                /*view.findViewById<View>(R.id.tv_app_name).translationX = -(pageWidth * position)
                view.findViewById<View>(R.id.tv_app_subtitle).translationX = -(pageWidth * position)

                view.findViewById<View>(R.id.userView).translationX = pageWidth * position
                view.findViewById<View>(R.id.tv_user_label).translationX = pageWidth * position
                view.findViewById<View>(R.id.view_user_underline).translationX = pageWidth * position
                view.findViewById<View>(R.id.profileImage).translationX = pageWidth * position

                view.findViewById<View>(R.id.passView).translationX = pageWidth * position
                view.findViewById<View>(R.id.tv_pass_label).translationX = pageWidth * position
                view.findViewById<View>(R.id.view_pass_underline).translationX = pageWidth * position
                view.findViewById<View>(R.id.iv_pass_icon).translationX = pageWidth * position

                view.findViewById<View>(R.id.commandButton).translationX = -(pageWidth * position)*/
            }
            else -> view.alpha = 0f
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val x = ((viewpager.width * position + positionOffsetPixels) * computeFactor())
        scrollView.scrollTo(x.toInt() + imageView.width / 3, 0)
    }

    override fun onPageSelected(position: Int) {
    }

    private fun computeFactor(): Float {
        return (imageView.width / 2 - viewpager.width) / (viewpager.width *
                viewpager.adapter!!.count - 1).toFloat()
    }

}