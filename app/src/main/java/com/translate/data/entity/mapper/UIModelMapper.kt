package com.translate.data.entity.mapper

import com.translate.data.entity.network.meaning.DefinitionResponseModel
import com.translate.data.entity.network.meaning.FullMeaningResponseModel
import com.translate.data.entity.network.meaning.ImageResponseModel
import com.translate.data.entity.network.translate.MeaningResponseModel
import com.translate.data.entity.network.translate.SearchResponseModel
import com.translate.data.entity.network.translate.TranslationResponseModel
import com.translate.data.entity.presentation.meaning.DefinitionUIModel
import com.translate.data.entity.presentation.meaning.FullMeaningUIModel
import com.translate.data.entity.presentation.translate.MeaningUIModel
import com.translate.data.entity.presentation.translate.TranslationUIModel

object UIModelMapper {

    fun map(response: List<SearchResponseModel>): List<MeaningUIModel> {
        val list = mutableListOf<MeaningUIModel>()
        response.forEach { list.addAll(mapListMeanings(it.meanings, it.text)) }
        return list
    }

    private fun mapListMeanings(response: List<MeaningResponseModel>?, textOnEng: String): List<MeaningUIModel> =
        response?.map { map(it, textOnEng) } ?: emptyList()

    private fun map(response: MeaningResponseModel, textOnEng: String): MeaningUIModel =
        response.run {
            MeaningUIModel(
                id,
                textOnEng,
                partOfSpeechCode,
                map(translation),
                previewUrl,
                imageUrl,
                transcription,
                soundUrl
            )
        }

    private fun map(response: TranslationResponseModel?): TranslationUIModel? =
        response?.run { TranslationUIModel(text, note) }

    fun map(response: FullMeaningResponseModel): FullMeaningUIModel =
        response.run {
            FullMeaningUIModel(
                id,
                wordId,
                mapListMeaningImgs(images),
                text,
                map(translation),
                map(definition),
                mapListExamples(examples)
            )
        }

    private fun mapListMeaningImgs(response: List<ImageResponseModel>?): List<String> {
        val list = mutableListOf<String>()
        response?.forEach { item -> item.url?.let { list.add(it) } }
        return list
    }

    private fun mapListExamples(response: List<DefinitionResponseModel>?): List<DefinitionUIModel> {
        val list = mutableListOf<DefinitionUIModel>()
        response?.forEach { item -> map(item)?.let { list.add(it) } }
        return list
    }

    private fun map(response: DefinitionResponseModel?): DefinitionUIModel? =
        response?.run { DefinitionUIModel(soundUrl, text) }
}