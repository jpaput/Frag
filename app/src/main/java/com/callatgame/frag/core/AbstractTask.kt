package com.callatgame.frag.core

import android.content.Context
import android.util.Log
import com.callatgame.frag.common.exception.AuthorizationException
import com.callatgame.frag.common.exception.CaGException
import com.callatgame.frag.model.Data
import com.callatgame.frag.common.exception.ErrorType
import com.callatgame.frag.starter.StarterActivity
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.schedulers.Schedulers
import java.io.IOException

abstract class AbstractTask<R : Data>(val mContext: Context) {

    private var single: Single<R>? = null

    init {
        single =
            Single.create { singleEmitter: SingleEmitter<R> ->
                try {
                    val result: R = doCall()
                    singleEmitter.onSuccess(result)
                } catch (backendException: CaGException) {
                    singleEmitter.onError(backendException)
                } catch (e: Exception) {
                    singleEmitter.onError(e)
                }
            }
    }

    open fun execute(requestCallback: RequestCallBack<R>) {
        single!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getSubscriber(requestCallback))

    }

    @Throws(
        IOException::class,
        InterruptedException::class,
        UndeliverableException::class,
        CaGException::class,
        AuthorizationException::class
    )
    protected abstract fun doCall(): R


    open fun getSubscriber(requestCallback: RequestCallBack<R>): SingleObserver<R> {
        return object : SingleObserver<R> {

            override fun onError(exception: Throwable) {

                when (exception) {
                    is CaGException -> requestCallback.onError(exception)
                    is AuthorizationException -> {
                        mContext.startActivity(StarterActivity.newIntent(mContext))
                    }
                    else -> {
                        Log.e(AbstractTask::class.java.simpleName, exception.message)
                        requestCallback.onError(
                            CaGException(
                                ErrorType.UNEXCEPTED_ERROR
                            )
                        )
                    }
                }
            }

            override fun onSubscribe(disposable: Disposable) {}

            override fun onSuccess(result: R) {
                requestCallback.onSuccess(result)
            }
        }
    }

}