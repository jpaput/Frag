package com.callatgame.frag.core

import android.content.Context
import com.callatgame.frag.R
import com.callatgame.frag.common.exception.AuthorizationException
import com.callatgame.frag.common.exception.CaGException
import com.callatgame.frag.common.exception.ErrorType
import retrofit2.Call
import java.io.IOException

abstract class AbstractService(var context : Context) {

    protected val apiEndPoint: ApiEndPoint

    init {
        apiEndPoint = NetworkUtil.getRetrofit(context).create(ApiEndPoint::class.java)
    }


    @Throws(
        IOException::class,
        CaGException::class)
    protected open fun <Result> execute(call: Call<Result>): Result? {

        try {
            if(!NetworkUtil.verifyAvailableNetwork(context)){
                throw CaGException(
                    ErrorType.NETWORK_ERROR,
                    context.getString(R.string.netword_unavailable_generic_error)
                )
            }
                val response = call.execute()

            if (response.isSuccessful) {
                return response.body()
            }

            when(response.code()){
                401 -> throw AuthorizationException()
                else -> throw CaGException(
                    ErrorType.BACKEND_ERROR,
                    response.errorBody()!!.string()
                )
            }

        } catch (exception : Exception) {
            throw exception
        }
    }
}

