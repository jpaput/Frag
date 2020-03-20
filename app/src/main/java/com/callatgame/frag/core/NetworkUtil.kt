package com.callatgame.frag.core

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import com.callatgame.frag.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class NetworkUtil {

    companion object{

        fun verifyAvailableNetwork(context: Context):Boolean{

            val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo= connectivityManager.activeNetworkInfo
            return  networkInfo!=null && networkInfo.isConnected
        }



        fun getRetrofit(context: Context) : Retrofit {

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val loggingInterceptor = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG) {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }else{
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            }

            val client =
                OkHttpClient.Builder()
                    .addInterceptor(object : Interceptor {
                        @Throws(IOException::class)
                        override fun intercept(chain: Interceptor.Chain): Response {
                            val original = chain.request()
                            val requestBuilder = original.newBuilder()
                                .addHeader("x-access-token", PreferenceManager(context).getToken())
                                .addHeader(
                                    "User-Agent",
                                    "Android " + Build.VERSION.RELEASE + "; CallAtGame " + BuildConfig.VERSION_NAME
                                )
                                .method(original.method, original.body)


                            return chain.proceed(requestBuilder.build())
                        }
                    })
                    .addInterceptor(loggingInterceptor)
                    .callTimeout(10000, TimeUnit.MILLISECONDS)
                    .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit
        }
    }

}