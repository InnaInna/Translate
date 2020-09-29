package com.translate.data.repository

import com.translate.data.entity.network.meaning.FullMeaningResponseModel
import com.translate.data.entity.network.translate.SearchResponseModel
import com.translate.data.network.TranslateApiService
import com.translate.domain.repository.ITranslateRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TranslateRepositoryImpl @Inject constructor(
    private val translateApiService: TranslateApiService
) : ITranslateRepository {

    override suspend fun getSearchList(search: String, page: Long, perPage: Long): List<SearchResponseModel> =
        translateApiService.getSearchList(search, page, perPage)

    override suspend fun getMeanings(id: String): List<FullMeaningResponseModel> =
        translateApiService.getMeanings(id)
}