package com.my.myredsoapp

import android.app.Application
import com.my.myredsoapp.network.ApiClient

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ApiClient.instance.init()
    }
}