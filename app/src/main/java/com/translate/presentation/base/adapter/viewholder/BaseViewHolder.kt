package com.translate.presentation.base.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<In>(parent: ViewGroup, @LayoutRes layoutId: Int) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {

    protected val context: Context = itemView.context

    abstract fun bind(item: In)
}