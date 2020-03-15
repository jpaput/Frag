package com.callatgame.frag.common.ui

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.callatgame.frag.R
import com.callatgame.frag.common.callback.LoaderCallback

class LoaderView : AppCompatImageView {

    private val TAG = LoaderView::class.java.simpleName

    private var souldStopLoot = false
    private val loader = this
    private var callBack : LoaderCallback? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        this.setImageResource(R.drawable.ic_progress_wheel)
        ImageViewCompat.setImageTintList(
            this,
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.antracite_grey))
        )

        this.visibility = View.GONE
    }

    fun setCallback(callback: LoaderCallback){
        this.callBack = callback;
    }

    fun start(){
        this.visibility = View.VISIBLE

        val scaleUp = AnimationUtils.loadAnimation(context, R.anim.scale_up)
        scaleUp.setAnimationListener(scaleUpAnimationListener)

        this.startAnimation(scaleUp)
    }

    fun stop(){
        souldStopLoot = true
    }

    private fun hide(){
        this.visibility = View.GONE

        if(callBack != null){
            callBack?.onLoaderStopped()
        }
    }


    val scaleUpAnimationListener = object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) {
            val rotate = AnimationUtils.loadAnimation(context, R.anim.rotate)
            rotate.repeatCount = Animation.INFINITE
            rotate.setAnimationListener(rotateAnimationListener)

            loader.startAnimation(rotate)
        }
        override fun onAnimationRepeat(animation: Animation) {}
    }

    val rotateAnimationListener = object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) {}
        override fun onAnimationRepeat(animation: Animation) {
            if(souldStopLoot){
                souldStopLoot = false
                val scaleDown = AnimationUtils.loadAnimation(context, R.anim.scale_down)
                scaleDown.setAnimationListener(scaleDownAnimationListener)
                loader.startAnimation(scaleDown)
            }
        }
    }

    val scaleDownAnimationListener = object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) { hide() }
        override fun onAnimationRepeat(animation: Animation) {}
    }


}