package com.callatgame.frag.core

import androidx.fragment.app.Fragment
import com.callatgame.frag.common.ui.SuccessDialog

abstract class AbstractFragment : Fragment() {

    private fun getAbstractActivity() : AbstractActivity {
        return activity as AbstractActivity
    }

    open fun showError(message: String) {
        getAbstractActivity().showError(message)
    }

    open fun showProgressDialog(){
        getAbstractActivity().showProgressDialog()
    }

    open fun hideProgressDialog(){
        getAbstractActivity().hideProgressDialog()
    }

    open fun showSuccessDialog(message: String) {
        getAbstractActivity().showSuccessDialog(message)
    }

    open fun showSuccessDialog() {
        showSuccessDialog("")
    }
}