package com.translate.presentation.main.fragment.translate.adapter

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.translate.data.entity.presentation.translate.MeaningUIModel
import com.translate.presentation.base.adapter.listeners.OnItemClickListener
import com.translate.presentation.base.adapter.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.li_translate.view.*

class TranslateViewHolder(
    private val itemListener: OnItemClickListener<MeaningUIModel>,
    parent: ViewGroup,
    @LayoutRes layoutId: Int
) : BaseViewHolder<MeaningUIModel>(parent, layoutId) {

    override fun bind(item: MeaningUIModel) {
        itemView.run {
            li_txt_suggestion.text = item.textOnEng
            li_txt_suggestion_translate.text = item.translation?.text
            li_txt_suggestion.setOnClickListener {
                itemListener.onItemClick(item)
            }
        }
    }
}