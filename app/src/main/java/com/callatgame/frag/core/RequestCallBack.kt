package com.callatgame.frag.core

import androidx.annotation.MainThread
import com.callatgame.frag.common.exception.CaGException

interface RequestCallBack<Result> {

    @MainThread
    fun onSuccess(result : Result)

    @MainThread
    fun onError(error : CaGException)

}