package com.callatgame.frag.core

import androidx.annotation.MainThread
import com.callatgame.frag.model.CaGError

interface RequestCallBack<Result> {

    @MainThread
    fun onSuccess(result : Result)

    @MainThread
    fun onError(error : CaGError)

}