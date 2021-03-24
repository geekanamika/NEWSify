package com.example.newsapp.data.local;

import com.google.gson.annotations.SerializedName

data class ArticleData(
    @SerializedName(value = "title")
    val articleTitle: String? = null,
    @SerializedName(value = "urlToImage")
    val imageUrl: String? = null,
    @SerializedName(value = "publishedAt")
    val publishedAt: String? = null
)

data class NewsResponse(
    @SerializedName("articles")
    val articles: List<ArticleData>? = null
)

