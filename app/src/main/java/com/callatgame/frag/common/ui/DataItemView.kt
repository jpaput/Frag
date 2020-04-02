package com.callatgame.frag.common.ui

import android.content.Context
import android.content.DialogInterface
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.callatgame.frag.R


class DataItemView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    enum class Type {
        GENDER, LEVEL, SIZE, WEIGHT, FREE_EDITING, DATE
    }

    lateinit var type: Type

    val labelTextView : TextView
    val valueTextView : TextView

    init {
        inflate(context, R.layout.data_item_view, this)

         labelTextView = findViewById(R.id.label_textview)
         valueTextView = findViewById(R.id.value_textview)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DataItemView)
        labelTextView.text = attributes.getString(R.styleable.DataItemView_label)
        type = Type.values().get(attributes.getInt(R.styleable.DataItemView_type, 0))
        attributes.recycle()

        when(type){
            Type.GENDER -> setupMultiSelection(R.array.genders_array)
            Type.LEVEL -> setupMultiSelection(R.array.level_array)
            Type.FREE_EDITING -> setupFreeEditing()
            Type.DATE -> setupDateSelection()
            Type.SIZE -> setupSizeSelection()
            Type.WEIGHT -> setupWeightSelection()
        }
    }

    private fun setupWeightSelection() {
    }

    private fun setupSizeSelection() {
    }

    private fun setupDateSelection() {
    }

    private fun setupFreeEditing() {
    }

    private fun setupMultiSelection(array: Int) {
        this.setOnClickListener{
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)

            builder.setItems(resources.getStringArray(array),
                DialogInterface.OnClickListener { dialog, which ->
                    valueTextView.text = resources.getStringArray(array)[which]
                    /*when (which) {
                        0, 1, 2, 3, 4 -> {
                        }
                    }*/
                })

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    fun setValue(value : String? ){

        if(value == null){
            valueTextView.text = "-"
        }else{
            valueTextView.text = value
        }
    }
}