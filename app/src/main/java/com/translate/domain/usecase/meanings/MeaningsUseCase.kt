package com.translate.domain.usecase.meanings

import com.translate.data.entity.mapper.UIModelMapper
import com.translate.data.entity.presentation.meaning.FullMeaningUIModel
import com.translate.domain.repository.ITranslateRepository
import com.translate.domain.usecase.BaseCoroutinesUseCase
import javax.inject.Inject

class MeaningsUseCase @Inject constructor(
    private val translateRepository: ITranslateRepository
) : BaseCoroutinesUseCase<FullMeaningUIModel?>() {

    private var meaningId: String? = null

    fun setData(meaningId: String){
        this.meaningId = meaningId
    }

    override suspend fun executeOnBackground(): FullMeaningUIModel? = meaningId?.let {
        val response = translateRepository.getMeanings(it)
        UIModelMapper.map(response.first())
    }
}