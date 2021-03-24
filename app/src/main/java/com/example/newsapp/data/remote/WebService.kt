package com.example.newsapp.data.remote

import com.example.newsapp.data.local.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Anamika Tripathi on 13/11/18.
 */
interface WebService {
    @GET("top-headlines")
    fun loadTopHeadlines(
        @Query("country") country: String?,
        @Query("apiKey") api_key: String?
    ): Call<NewsResponse?>
}
