package com.example.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.models.Category
import kotlinx.android.synthetic.main.item_news_category.view.*

/**
 * Created by Anamika Tripathi on 23/3/21.
 */
class CategoryListAdapter(private val listener: CategoryClickListener) :
    RecyclerView.Adapter<CategoryListAdapter.CategoryItemViewHolder>() {

    var categoryList: List<Category> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news_category, parent, false)
        return CategoryItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_news_category

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(categoryList[position], listener)
    }

    inner class CategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(category: Category, listener: CategoryClickListener) {
            itemView.item_news_category.text = category.categoryName
            itemView.setOnClickListener{listener.onClick()}
            // Todo set background image using colors change
//            Glide.with(itemView.context)
//                .load(newsItem.imageURL)
//                .apply(RequestOptions().circleCrop())
//                .into(itemView.item_news_thumbnail)
        }

    }

    interface CategoryClickListener {
        fun onClick()
    }
}
