package com.translate.data.network

import com.translate.common.NetworkConstants.GET_MEANING_LIST
import com.translate.common.NetworkConstants.GET_SEARCH_LIST
import com.translate.common.NetworkConstants.ID
import com.translate.common.NetworkConstants.PAGE
import com.translate.common.NetworkConstants.PER_PAGE
import com.translate.common.NetworkConstants.SEARCH
import com.translate.data.entity.network.meaning.FullMeaningResponseModel
import com.translate.data.entity.network.translate.SearchResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslateApiService {

    @GET(GET_SEARCH_LIST)
    suspend fun getSearchList(
        @Query(SEARCH) companyId: String,
        @Query(PAGE) page: Long,
        @Query(PER_PAGE) perPage: Long) : List<SearchResponseModel>

    @GET(GET_MEANING_LIST)
    suspend fun getMeanings(@Query(ID) id: String) : List<FullMeaningResponseModel>
}