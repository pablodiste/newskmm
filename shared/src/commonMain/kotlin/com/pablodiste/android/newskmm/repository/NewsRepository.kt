package com.pablodiste.android.newskmm.repository

import com.pablodiste.android.newskmm.api.NewsApi
import com.pablodiste.android.newskmm.domain.NewsResponse
import com.pablodiste.android.newskmm.domain.Response
import com.pablodiste.newskmm.NewsDatabase

class NewsRepository(val newsApi: NewsApi, val newsDatabase: NewsDatabase) {

    suspend fun getBreakingNews(): Response<NewsResponse> {
        return newsApi.getBreakingNews()
    }
}