package com.example.newsapp

import androidx.lifecycle.LiveData
import com.example.newsapp.data.models.ArticleData
import com.example.newsapp.data.models.Category
import com.example.newsapp.data.remote.AppNetworkSource

/**
 * Created by Anamika Tripathi on 23/03/21
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

    fun startFetchingData(categoryName: String) {
        networkHelper.loadNewsArticles(categoryName)
    }

    fun getCategories(): List<Category> {
        return networkHelper.getCategories()
    }

    fun isLoading(): LiveData<Boolean> {
        return networkHelper.isLoading
    }

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
