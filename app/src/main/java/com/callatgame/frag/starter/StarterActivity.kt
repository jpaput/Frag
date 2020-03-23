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
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
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
            position <= 1 -> { }
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