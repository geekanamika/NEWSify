package com.example.newsapp.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.BuildConfig
import com.example.newsapp.data.local.ArticleData
import com.example.newsapp.data.local.NewsResponse
import com.example.newsapp.utils.AppConstants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

/**
 * Created by Anamika Tripathi on 23/03/21
 */

class AppNetworkSource private constructor() {

    private var webService: WebService? = null
    // mutable list which contains values from network source
    val mDownloadedNewsArticles: MutableLiveData<List<ArticleData>>
    // checks about loading status & helps in loading indicator
    val isLoading: MutableLiveData<Boolean>
    private var retrofit: Retrofit? = null

    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }

    init {
        if (retrofit == null) {
            retrofit = getRetrofit()
        }
        webService = retrofit!!.create(WebService::class.java)
        mDownloadedNewsArticles = MutableLiveData()
        isLoading = MutableLiveData()
    }

    fun loadNewsArticles() {
        isLoading.postValue(true)
        val newsResponse: Call<NewsResponse?> = webService!!.loadTopHeadlines(AppConstants.COUNTRY, BuildConfig.NewsApiKey)

        newsResponse.enqueue(object : Callback<NewsResponse?> {
            override fun onResponse(
                call: Call<NewsResponse?>,
                response: Response<NewsResponse?>
            ) {
                if (response.isSuccessful) { // posting value to the live data
                    mDownloadedNewsArticles.postValue(response.body()?.articles)
                    isLoading.postValue(false)
                } else {
                    isLoading.postValue(false)
                    Timber.e("NewsResponse: response was not successful")
                }
            }

            override fun onFailure(
                call: Call<NewsResponse?>,
                t: Throwable
            ) {
                isLoading.postValue(false)
                Timber.e("NewsResponse: onFailure called inside retrofit loading")
            }
        })
    }

    private fun getRetrofit(): Retrofit { // Add all interceptors you want (headers, URL, logging, stetho logs)
        val httpClient = OkHttpClient.Builder()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)) // Add your adapter factory to handler Errors
            .client(httpClient.build())
            .build()
    }

    companion object {
        // mutable list which contains values from network source
        // For Singleton instantiation
        private val LOCK = Any()
        private var sInstance: AppNetworkSource? = null
        /**
         * Get the singleton for this class
         */
        val instance: AppNetworkSource?
            get() {
                if (sInstance == null) {
                    synchronized(
                        LOCK
                    ) { sInstance = AppNetworkSource() }
                }
                return sInstance
            }
    }
}
