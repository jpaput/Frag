package com.callatgame.frag.common.ui

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import com.callatgame.frag.R
import com.google.android.material.textfield.TextInputLayout

class ImprovedTextInputLayout : TextInputLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun setError(errorText: CharSequence?) {
        super.setError(errorText)
        this.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.error_shake_animation
            )
        )
    }

}