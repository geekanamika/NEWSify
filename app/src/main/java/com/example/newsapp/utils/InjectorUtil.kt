package com.example.newsapp.utils

import android.content.Context
import com.example.newsapp.AppNewsRepository
import com.example.newsapp.data.remote.AppNetworkSource

/**
 * Created by Anamika Tripathi on 23/03/21
 */
object InjectorUtil {
    fun provideRepository(context: Context?): AppNewsRepository? {
        val executors = AppExecutors.instance
        // remote
        val networkDataSource = AppNetworkSource.instance
        return AppNewsRepository.getInstance(networkDataSource!!)
    }
}
