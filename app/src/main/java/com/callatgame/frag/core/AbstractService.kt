package com.callatgame.frag.core

import android.content.Context
import android.util.Log
import com.callatgame.frag.R
import com.callatgame.frag.model.ErrorType
import com.callatgame.frag.model.CaGError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

abstract class AbstractService<T>(var context : Context) {

    val apiEndPoint: ApiEndPoint

    init {
        apiEndPoint = NetworkUtil.getRetrofit().create(ApiEndPoint::class.java)
    }


    protected fun <Result> execute(call: Call<Result>, callback: RequestCallBack<Result> ) {
        try {
            if(!NetworkUtil.verifyAvailableNetwork(context)){
                CoroutineScope(Dispatchers.Main).launch {
                    callback.onError(
                        CaGError(
                            ErrorType.NETWORK_ERROR,
                            context.getString(R.string.netword_unavailable_generic_error)
                        )
                    )
                }
                return
            }
            val response= call.execute()

            CoroutineScope(Dispatchers.Main).launch {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onSuccess(it) }
                } else {
                    callback.onError(
                        CaGError(ErrorType.BACKEND_ERROR, response.errorBody()!!.string())
                    )
                }
            }
        } catch (e: Exception) {
            Log.e(AbstractService::class.java.simpleName, e.toString())

            CoroutineScope(Dispatchers.Main).launch {
                callback.onError(
                    CaGError(
                        ErrorType.UNEXCEPTED_ERROR,
                        context.getString(R.string.unexcepted_generic_error)
                    )
                )
            }
        }
    }
}

