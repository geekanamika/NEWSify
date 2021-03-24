package com.example.newsapp.data

import com.example.newsapp.data.models.Category
import com.example.newsapp.data.models.NewsItem

/**
 * Created by Anamika Tripathi on 23/3/21.
 */

fun getTopHeadlines(): List<NewsItem> {
    return listOf(
        NewsItem(
            title = "This one is for my dad’: Krunal Pandya breaks down in tears after smashing fastest 50 by ODI debutant - The Indian Express",
            imageURL = "https://images.indianexpress.com/2021/03/krunal-pandya.jpg",
            publishedAt = "2021-03-23T12:50:10Z"
        ),
        NewsItem(
            title = "What today’s Supreme Court order on loan moratorium means for banks - Mint",
            imageURL = "https://images.indianexpress.com/2021/03/krunal-pandya.jpg",
            publishedAt = "2021-03-23T12:50:10Z"
        ),
        NewsItem(
            title = "Covid-19 Vaccine & Pregnancy | Medically Speaking With Shalini Bhardwaj | NewsX - NewsX",
            imageURL = "https://images.indianexpress.com/2021/03/krunal-pandya.jpg",
            publishedAt = "2021-03-23T12:50:10Z"
        ),
        NewsItem(
            title = "What today’s Supreme Court order on loan moratorium means for banks - Mint",
            imageURL = "https://images.indianexpress.com/2021/03/krunal-pandya.jpg",
            publishedAt = "2021-03-23T12:50:10Z"
        ),
        NewsItem(
            title = "This one is for my dad’: Krunal Pandya breaks down in tears after smashing fastest 50 by ODI debutant - The Indian Express",
            imageURL = "https://images.indianexpress.com/2021/03/krunal-pandya.jpg",
            publishedAt = "2021-03-23T12:50:10Z"
        )
    )
}

fun getCategories(): List<Category> {
    return listOf(
        Category(categoryName = "Entertainment"),
        Category(categoryName = "Sports"),
        Category(categoryName = "Technology"),
        Category(categoryName = "Business")
    )
}
