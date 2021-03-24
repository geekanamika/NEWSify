package com.example.newsapp

import androidx.lifecycle.LiveData
import com.example.newsapp.data.local.ArticleData
import com.example.newsapp.data.remote.AppNetworkSource

/**
 * Created by Anamika Tripathi on 12/11/18.
 */
class AppNewsRepository private constructor(
    private val networkHelper: AppNetworkSource
) {

    /**
     * network related methods
     */
    fun getTopNewsHeadlines(): LiveData<List<ArticleData>> {
        return networkHelper.mDownloadedNewsArticles
    }
    fun startFetchingData() {
        networkHelper.loadNewsArticles()
    }

    val isLoadingData: LiveData<Boolean> get() = networkHelper.isLoading

    companion object {
        // For Singleton instantiation
        private val LOCK = Any()
        private var sInstance: AppNewsRepository? = null
        @Synchronized
        fun getInstance(
            networkDataSource: AppNetworkSource
        ): AppNewsRepository? {
            if (sInstance == null) {
                synchronized(
                    LOCK
                ) {
                    sInstance =
                        AppNewsRepository(networkDataSource)
                }
            }
            return sInstance
        }
    }

}
