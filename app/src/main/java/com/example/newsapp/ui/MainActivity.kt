package com.example.newsapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.getCategories
import com.example.newsapp.data.getTopHeadlines
import com.example.newsapp.ui.adapter.CategoryListAdapter
import com.example.newsapp.ui.adapter.NewsListAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), CategoryListAdapter.CategoryClickListener {

    private lateinit var categoryAdapter: CategoryListAdapter
    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        fetchData()
    }

    override fun onClick() {
        Toast.makeText(this, "category clicked", Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        categoryAdapter = CategoryListAdapter(this@MainActivity)
        newsListAdapter = NewsListAdapter()
        newsItemRecyclerView.adapter = newsListAdapter
        categoryRecyclerView.adapter = categoryAdapter
        categoryRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    // Todo use observer here
    private fun fetchData() {
        val newsItemList = getTopHeadlines().sortedBy { it.publishedAt }
        newsListAdapter.newsFeedList = newsItemList
        newsListAdapter.notifyItemRangeInserted(0, newsItemList.size)
        newsItemRecyclerView.scrollToPosition(0)

        val categoryList = getCategories()
        categoryAdapter.categoryList = categoryList
        categoryAdapter.notifyItemRangeInserted(0, categoryList.size)
        categoryRecyclerView.scrollToPosition(0)
    }
}


