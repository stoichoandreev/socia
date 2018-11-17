package com.sniper.social.base.adapter

import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class BaseAdapterDelegate<T, VH : BaseViewHolder> {

    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    @CallSuper
    open fun bindViewHolder(holder: VH, model: T, position: Int, listener: OnItemClickListener<T>) {
        //unused
    }
}
