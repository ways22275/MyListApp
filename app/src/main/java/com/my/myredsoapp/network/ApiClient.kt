package com.my.myredsoapp.network

import com.my.myredsoapp.BuildConfig
import com.my.myredsoapp.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {

    private lateinit var mRedSoService : ApiRedSoService
    private lateinit var mGitHubService : ApiGitHubService

    private object Holder {
        val INSTANCE = ApiClient()
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }

    fun init() {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE))
            .build()

        val retrofitRedSo = Retrofit.Builder()
            .baseUrl(Constant.REDSO)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        val retrofitGitHub = Retrofit.Builder()
            .baseUrl(Constant.GITHUB)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()


        mRedSoService = retrofitRedSo.create(ApiRedSoService::class.java)
        mGitHubService = retrofitGitHub.create(ApiGitHubService::class.java)
    }

    fun getRedSoService() : ApiRedSoService {
        return mRedSoService
    }

    fun getGitHubService() : ApiGitHubService {
        return mGitHubService
    }
}