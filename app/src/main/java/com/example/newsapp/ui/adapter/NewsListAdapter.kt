package com.example.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.R
import com.example.newsapp.data.models.NewsItem
import kotlinx.android.synthetic.main.item_news_article.view.*

/**
 * Created by Anamika Tripathi on 23/3/21.
 */
class NewsListAdapter() :
    RecyclerView.Adapter<NewsListAdapter.NewsItemViewHolder>() {

    var newsFeedList: List<NewsItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news_article, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_news_article


    override fun getItemCount(): Int {
        return newsFeedList.size
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(newsFeedList[position])
    }

    inner class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(newsItem: NewsItem) {
            itemView.item_news_title.text = newsItem.title
            itemView.item_published_time.text = newsItem.publishedAt
            Glide.with(itemView.context)
                .load(newsItem.imageURL)
                .apply(RequestOptions().circleCrop())
                .into(itemView.item_news_thumbnail)
        }

    }
}
