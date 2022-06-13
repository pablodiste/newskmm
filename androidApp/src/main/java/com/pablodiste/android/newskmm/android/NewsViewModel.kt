package com.pablodiste.android.newskmm.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablodiste.android.newskmm.domain.NewsResponse
import com.pablodiste.android.newskmm.domain.Response
import com.pablodiste.android.newskmm.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

val TAG = NewsViewModel::class.simpleName

class NewsViewModel: ViewModel(), KoinComponent {

    val news: MutableStateFlow<Response<NewsResponse>> = MutableStateFlow(Response.Empty())
    //private val repository: NewsRepository by di.instance()
    private val repository: NewsRepository by inject()

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            val response = repository.getBreakingNews()
            Log.d(TAG, "Results: $response")
            news.value = response
        }
    }

}