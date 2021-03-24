package com.example.newsapp

import android.app.Application
import android.content.Context
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by Anamika Tripathi on 23/03/21.
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
