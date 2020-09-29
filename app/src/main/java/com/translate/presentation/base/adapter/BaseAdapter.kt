package com.translate.presentation.base.adapter

import androidx.recyclerview.widget.RecyclerView
import com.translate.presentation.base.adapter.viewholder.BaseViewHolder

abstract class BaseAdapter<In, Vh : BaseViewHolder<In>> : RecyclerView.Adapter<Vh>() {

    companion object{
        private const val NO_INDEX = -1
    }

    private var list = mutableListOf<In>()

    override fun onBindViewHolder(holder: Vh, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = list.size

    fun getItems(): List<In>? = list

    fun setItems(list: List<In>?) {
        list?.let {
            this.list.clear()
            this.list = it.toMutableList()
            notifyDataSetChanged()
        }
    }

    fun addItem(item: In) {
        list.add(item)
        notifyItemInserted(list.lastIndex)
    }

    fun addItems(items: List<In>?) {
        items?.let {
            val index = list.lastIndex
            list.addAll(it)
            notifyItemRangeChanged(index, list.size)
        }
    }

    fun updateItem(item: In, position: Int) {
        list[position] = item
        notifyItemChanged(position)
    }

    open fun getItem(position: Int): In? = list.getOrNull(position)

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeItem(item: In) {
        val position = list.indexOf(item)
        if (position != NO_INDEX) {
            removeItem(position)
        }
    }

    fun removeItems(items: List<In>) {
        val position = list.lastIndex
        list.removeAll(items)
        notifyItemRangeRemoved(position, items.size)
    }
}