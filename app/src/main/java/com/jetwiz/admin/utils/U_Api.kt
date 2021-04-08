package com.jetwiz.admin.utils

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.jetwiz.admin.base.CST
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object U_Api {
    val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(CST.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private val okHttpClient: OkHttpClient
        get() {
            val logging = HttpLoggingInterceptor()
            val clientBuilder = OkHttpClient.Builder()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS) // def: 10 secs
            return clientBuilder.build()
        }
}
