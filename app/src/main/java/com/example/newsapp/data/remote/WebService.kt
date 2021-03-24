package com.example.newsapp.data.remote

import com.example.newsapp.data.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Anamika Tripathi on 23/03/21
 */
interface WebService {
    @GET("top-headlines")
    fun loadTopHeadlines(
        @Query("country") country: String?,
        @Query("apiKey") api_key: String?
    ): Call<NewsResponse?>
}
