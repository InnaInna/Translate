package com.translate.domain.repository

import com.translate.data.entity.network.meaning.FullMeaningResponseModel
import com.translate.data.entity.network.translate.SearchResponseModel

interface ITranslateRepository {

    suspend fun getSearchList(search: String, page: Long, pageSize: Long): List<SearchResponseModel>
    suspend fun getMeanings(id: String) : List<FullMeaningResponseModel>
}