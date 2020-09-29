package com.translate.domain.usecase.translate

import com.translate.data.entity.mapper.UIModelMapper
import com.translate.data.entity.presentation.translate.MeaningUIModel
import com.translate.domain.repository.ITranslateRepository
import com.translate.domain.usecase.BasePageCoroutinesUseCase
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val translateRepository: ITranslateRepository
) : BasePageCoroutinesUseCase<List<MeaningUIModel>>() {

    private var text: String? = null

    fun setData(text: String?) {
        this.text = text
    }

    override suspend fun executeOnBackground(): List<MeaningUIModel> =
        text?.let {
            val response = translateRepository.getSearchList(it, page, getPerPage())
            total = response.size.toLong()
            UIModelMapper.map(response)
        } ?: emptyList()
}