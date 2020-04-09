package com.callatgame.frag.common.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.callatgame.frag.R
import com.callatgame.frag.common.exception.CaGException
import com.callatgame.frag.core.RequestCallBack
import com.callatgame.frag.main.task.UpdateDataTask
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.Gender
import com.callatgame.frag.utils.DateUtil
import java.text.SimpleDateFormat
import java.util.*


class DataItemView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    enum class Field {
        not_defined, gender, firstname, lastname, dob, size, weight, attack, defense, speed, stamina, aim, technic
    }

    enum class DataType {
        USER, PLAYER
    }

    var label: String
    var field: Field
    var dataType :DataType

    val labelTextView : TextView
    val valueTextView : TextView

    init {
        inflate(context, R.layout.data_item_view, this)

         labelTextView = findViewById(R.id.label_textview)
         valueTextView = findViewById(R.id.value_textview)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DataItemView)
        label = attributes.getString(R.styleable.DataItemView_label).toString()
        field = Field.values().get(attributes.getInt(R.styleable.DataItemView_field, 0))
        dataType = DataType.values().get(attributes.getInt(R.styleable.DataItemView_dataType, 0))

        attributes.recycle()

        labelTextView.text = label

        when(field){
            Field.gender -> setupGenderSelection()
            Field.firstname, Field.lastname -> setupFreeEditing(R.string.standart_hint,  InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS)
            Field.dob -> setupDateSelection()
            Field.attack,
            Field.defense,
            Field.speed,
            Field.stamina,
            Field.aim,
            Field.technic -> setupMultiSelection(R.array.level_array)
            Field.size -> setupFreeEditing(R.string.our_size_hint, InputType.TYPE_CLASS_NUMBER)
            Field.weight -> setupFreeEditing(R.string.our_weight_hint, InputType.TYPE_CLASS_NUMBER)
        }
    }

    private fun setupDateSelection() {

        this.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(
                context,
                R.style.DatePicker,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    updateValue(SimpleDateFormat(DateUtil.POSTGRE_TIME_FORMAT, Locale.US).format(cal.time))
                },
                year, month, day
            ).show()
        }
    }

    private fun setupFreeEditing(hintRest : Int, inputType :Int) {
        this.setOnClickListener{

            val input = EditText(context)
            val lp = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            input.layoutParams = lp
            input.hint = resources.getString(hintRest)
            input.inputType = inputType

            AlertDialog.Builder(context)
                .setView(input)
                .setTitle(resources.getString(R.string.free_editing_title, label))
                .setPositiveButton(resources.getString(R.string.validate),
                    DialogInterface.OnClickListener { dialog, which ->
                        updateValue(input.getText().toString())
                    })
                .setNegativeButton(resources.getString(R.string.cancel),
                    DialogInterface.OnClickListener { dialog, which -> })
                .show()
        }
    }

    private fun updateValue(newValue: String) {

        UpdateDataTask(context, dataType, field, newValue).execute(  object : RequestCallBack<DefaultResponse> {

            override fun onSuccess(result: DefaultResponse) {
                setValue(newValue)
            }

            override fun onError(error: CaGException) {
            }
        })
    }

    private fun setupMultiSelection(array: Int) {
        this.setOnClickListener{
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)

            builder.setItems(resources.getStringArray(array),
                DialogInterface.OnClickListener { dialog, which ->

                    updateValue(resources.getStringArray(array)[which])
                })

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    private fun setupGenderSelection() {
        this.setOnClickListener{
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)

            builder.setItems(resources.getStringArray(R.array.genders_array),
                DialogInterface.OnClickListener { dialog, position ->
                    when(position){
                        0 -> updateValue("M")
                        1 -> updateValue("F")
                    }
                })

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    fun setValue(value : String? ){

        val displayableValue : String

        if(value == null){
            displayableValue = "-"
        }else{
            when(field){
                Field.size -> displayableValue = resources.getString(R.string.cm, value)
                Field.weight -> displayableValue = resources.getString(R.string.kg, value)
                Field.dob -> {
                    val date : Date = SimpleDateFormat(DateUtil.POSTGRE_TIME_FORMAT, Locale.US).parse(value)
                    displayableValue = SimpleDateFormat(DateUtil.DISPLAYABLE_TIME_FORMAT, Locale.FRANCE).format(date)
                }
                Field.gender -> displayableValue = resources.getString(Gender.fromValue(value)!!.res)
                else -> displayableValue = value
            }
        }

        valueTextView.text = displayableValue
    }
}