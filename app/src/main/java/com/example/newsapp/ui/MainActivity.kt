package com.example.newsapp.ui

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.models.ArticleData
import com.example.newsapp.data.models.Category
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

        val text: Spannable = SpannableString("NEWSify")
        text.setSpan(
            ForegroundColorSpan(Color.BLACK),
            0,
            text.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        supportActionBar?.title = text
        initRecyclerView()
        initViewModels()
    }

    private fun initViewModels() {
        articleListViewModel = ViewModelProviders.of(this).get(ArticleListViewModel::class.java)
        articleListViewModel?.startFetchingData()
        articleListViewModel?.getLoadingStatus()?.observe(this, Observer {
            isLoading ->

            isLoading?.let { isLoading ->
                if (isLoading) {
                    indeterminateBar.visibility = View.VISIBLE
                } else {
                    indeterminateBar.visibility = View.GONE
                }

            }
        })
        articleListViewModel?.getNewsNetworkLiveData()?.observe(this,
            Observer<List<ArticleData>> { articles ->
                if (articles != null) {
                    newsListAdapter.newsFeedList = articles
                    newsListAdapter.notifyDataSetChanged()
                    newsItemRecyclerView.scrollToPosition(0)
            }}
        )
        val list = articleListViewModel?.getCategoryList()!!
        categoryAdapter.categoryList = list
        categoryAdapter.notifyDataSetChanged()
        categoryRecyclerView.scrollToPosition(0)
    }

    override fun onClick(category: Category) {
        articleListViewModel?.startFetchingData(category.categoryApiName)
    }

    private fun initRecyclerView() {
        categoryAdapter = CategoryListAdapter(this@MainActivity)
        newsListAdapter = NewsListAdapter()
        newsItemRecyclerView.adapter = newsListAdapter
        categoryRecyclerView.adapter = categoryAdapter
        categoryRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}


