package com.sniper.social.home.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sniper.social.base.adapter.BaseViewHolder
import com.sniper.social.base.adapter.OnItemClickListener
import com.sniper.social.converter.posts.PostViewModel
import java.util.*

class PostsAdapter(private val listener: OnItemClickListener<PostViewModel>) : RecyclerView.Adapter<BaseViewHolder>() {

    private val postItemDelegate = PostItemDelegate()
    private val adapterItems = ArrayList<PostViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val holder: RecyclerView.ViewHolder = postItemDelegate.createViewHolder(parent)
        return holder as BaseViewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val viewModel = adapterItems[position]
        postItemDelegate.bindViewHolder(holder as PostItemDelegate.PostItemViewHolder, viewModel, position, listener)
    }

    override fun getItemCount(): Int = adapterItems.size

    fun setNewItems(newItems: List<PostViewModel>) {
        adapterItems.clear()
        adapterItems.addAll(newItems)
        notifyDataSetChanged()
    }

}
