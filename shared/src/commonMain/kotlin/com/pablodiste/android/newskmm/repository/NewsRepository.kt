package com.pablodiste.android.newskmm.repository

import com.pablodiste.android.newskmm.api.NewsApi
import com.pablodiste.android.newskmm.domain.Article
import com.pablodiste.android.newskmm.domain.NewsResponse
import com.pablodiste.android.newskmm.domain.Response
import com.pablodiste.newskmm.NewsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsRepository(val newsApi: NewsApi, val newsDatabase: NewsDatabase) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val newsQueries = newsDatabase.articleQueries
    val state = MutableStateFlow<Response<NewsResponse>>(Response.Success(NewsResponse(emptyList())))

    suspend fun getBreakingNews(): Response<NewsResponse> {
        val breakingNews = newsApi.getBreakingNews()
        if (breakingNews is Response.Success) {
            breakingNews.data.articles.map { it.generateId() }
        }
        return breakingNews
    }

    fun fetchBreakingNews() {
        coroutineScope.launch {
            val breakingNews = getBreakingNews()
            state.value = breakingNews
        }
    }

    suspend fun getBreakingNewsWithCache(): Response<NewsResponse> {
        val breakingNews = getBreakingNews()
        if (breakingNews is Response.Success) {
            cache(breakingNews.data.articles)
        }
        return breakingNews
    }

    fun getCachedNews(): Response<NewsResponse> {
        val cachedData = newsQueries.selectAll().executeAsList().map { Article(id = it.id.toInt(), title = it.title) }
        return Response.Success(NewsResponse(articles = cachedData))
    }

    private fun cache(articles: List<Article>) {
        newsQueries.transaction {
            newsQueries.deleteAll()
            articles.forEach {
                newsQueries.insert(it.id!!.toLong(), it.title!!)
            }
        }
    }

}