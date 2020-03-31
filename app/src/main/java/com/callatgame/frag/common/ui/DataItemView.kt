package com.callatgame.frag.common.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.callatgame.frag.R

class DataItemView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val labelTextView : TextView
    val valueTextView : TextView

    init {
        inflate(context, R.layout.data_item_view, this)

         labelTextView = findViewById(R.id.label_textview)
         valueTextView = findViewById(R.id.value_textview)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DataItemView)
        labelTextView.text = attributes.getString(R.styleable.DataItemView_label)
        attributes.recycle()
    }

    public fun setValue(value :String){
        valueTextView.text = value
    }
}