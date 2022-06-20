package com.pablodiste.android.newskmm

import com.pablodiste.android.newskmm.repository.NewsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper: KoinComponent {
    val newsRepository: NewsRepository by inject()
    fun newsRepository() = newsRepository
}