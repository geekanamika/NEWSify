package com.example.newsapp.ui

import android.app.Application

import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newsapp.AppNewsRepository
import com.example.newsapp.data.local.ArticleData
import com.example.newsapp.data.models.Category
import com.example.newsapp.utils.InjectorUtil
import timber.log.Timber

/**
 * Created by Anamika Tripathi on 23/03/21.
 */
class ArticleListViewModel(@NonNull application: Application) :
    AndroidViewModel(application) {
    private var newsNetworkLiveData: LiveData<List<ArticleData>>? = null
    private var repository: AppNewsRepository? = null
    private var isLoading: LiveData<Boolean>? = null

    fun startFetchingData() {
        Timber.d("ArticleListViewModel: started fetching top headlines")
        repository?.startFetchingData()
    }

    fun getNewsNetworkLiveData(): LiveData<List<ArticleData>>? {
        return newsNetworkLiveData
    }

    fun getLoadingStatus(): LiveData<Boolean>? {
        return isLoading
    }

    fun getCategoryList(): List<Category>? {
        return repository?.getCategories()
    }

    init {
        repository = InjectorUtil.provideRepository(application.applicationContext)
        newsNetworkLiveData = repository?.getTopNewsHeadlines()
        isLoading = repository?.isLoading()
    }
}
