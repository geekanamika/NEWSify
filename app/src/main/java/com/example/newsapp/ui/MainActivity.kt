package com.example.newsapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.getCategories
import com.example.newsapp.data.getTopHeadlines
import com.example.newsapp.data.local.ArticleData
import com.example.newsapp.ui.adapter.CategoryListAdapter
import com.example.newsapp.ui.adapter.NewsListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CategoryListAdapter.CategoryClickListener {

    private lateinit var categoryAdapter: CategoryListAdapter
    private lateinit var newsListAdapter: NewsListAdapter
    private var articleListViewModel: ArticleListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModels()
        initRecyclerView()
        fetchData()
    }

    private fun initViewModels() {
        articleListViewModel = ViewModelProviders.of(this).get(ArticleListViewModel::class.java)
        articleListViewModel?.startFetchingData()
        articleListViewModel?.getNewsNetworkLiveData()?.observe(this,
            Observer<List<ArticleData>> { articles ->
                if (articles != null) {
                    newsListAdapter.newsFeedList = articles
                    newsListAdapter.notifyItemRangeInserted(0, articles.size)
                    newsItemRecyclerView.scrollToPosition(0)
            }}
        )
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
        val categoryList = getCategories()
        categoryAdapter.categoryList = categoryList
        categoryAdapter.notifyItemRangeInserted(0, categoryList.size)
        categoryRecyclerView.scrollToPosition(0)
    }
}


