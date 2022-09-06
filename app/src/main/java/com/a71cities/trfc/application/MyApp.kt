package com.a71cities.trfc.application

import android.app.Application
import com.a71cities.trfc.extras.Pref

class MyApp: Application() {

    companion object {
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Pref.init(instance)
    }
}