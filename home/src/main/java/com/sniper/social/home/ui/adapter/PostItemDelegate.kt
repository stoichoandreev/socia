package com.sniper.social.home.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sniper.social.base.adapter.BaseAdapterDelegate
import com.sniper.social.base.adapter.BaseViewHolder
import com.sniper.social.base.adapter.OnItemClickListener
import com.sniper.social.converter.posts.PostViewModel
import com.sniper.social.home.R
import kotlinx.android.synthetic.main.item_post_view.view.*

class PostItemDelegate : BaseAdapterDelegate<PostViewModel, PostItemDelegate.PostItemViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostItemViewHolder(inflater.inflate(R.layout.item_post_view, parent, false))
    }

    override fun bindViewHolder(holder: PostItemViewHolder,
                                model: PostViewModel,
                                position: Int,
                                listener: OnItemClickListener<PostViewModel>) {

        holder.parent.setOnClickListener{ listener.onItemClick(model)}
        holder.title.text = model.title
        holder.additionalInformation.text = model.body

        super.bindViewHolder(holder, model, position, listener)
    }

    class PostItemViewHolder(var parent: View) : BaseViewHolder(parent) {
        val title: TextView = parent.home_item_title_view
        val additionalInformation: TextView = parent.home_item_additional_information_view
    }
}
