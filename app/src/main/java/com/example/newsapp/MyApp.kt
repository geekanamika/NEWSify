package com.example.newsapp

import android.app.Application
import android.content.Context
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by Anamika Tripathi on 12/11/18.
 */
class MyApp : Application() {
    var mInstance: MyApp? = null
        get() = field

    override fun onCreate() {
        super.onCreate()
        mInstance = applicationContext as MyApp
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}
