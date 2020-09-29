package com.translate.presentation.main.fragment.translate.adapter

import android.view.ViewGroup
import com.translate.R
import com.translate.data.entity.presentation.translate.MeaningUIModel
import com.translate.presentation.base.adapter.BaseAdapter
import com.translate.presentation.base.adapter.listeners.OnItemClickListener

class TranslateAdapter constructor(
    private val itemListener: OnItemClickListener<MeaningUIModel>
) : BaseAdapter<MeaningUIModel, TranslateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslateViewHolder =
        TranslateViewHolder(itemListener, parent, R.layout.li_translate)
}