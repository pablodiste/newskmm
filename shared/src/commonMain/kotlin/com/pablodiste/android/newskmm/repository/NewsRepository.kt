package com.pablodiste.android.newskmm.repository

import com.pablodiste.android.newskmm.api.NewsApi
import com.pablodiste.android.newskmm.domain.NewsResponse
import com.pablodiste.android.newskmm.domain.Response

class NewsRepository(val newsApi: NewsApi) {

    suspend fun getBreakingNews(): Response<NewsResponse> {
        return newsApi.getBreakingNews()
    }
}