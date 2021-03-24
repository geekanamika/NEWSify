package com.example.newsapp

import androidx.lifecycle.LiveData
import com.example.newsapp.data.local.ArticleData

/**
 * Created by Anamika Tripathi on 12/11/18.
 */
internal interface INewsRepository {
    val topNewsHeadlines: LiveData<List<ArticleData>>
    fun startFetchingData(sourceOfNews: String?)
    val isLoadingData: LiveData<Boolean>
}
