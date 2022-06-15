package com.pablodiste.android.newskmm.repository

import com.pablodiste.android.newskmm.api.NewsApi
import com.pablodiste.android.newskmm.domain.Article
import com.pablodiste.android.newskmm.domain.NewsResponse
import com.pablodiste.android.newskmm.domain.Response
import com.pablodiste.newskmm.NewsDatabase

class NewsRepository(val newsApi: NewsApi, val newsDatabase: NewsDatabase) {

    val newsQueries = newsDatabase.articleQueries

    suspend fun getBreakingNews(): Response<NewsResponse> {
        return newsApi.getBreakingNews()
    }

    suspend fun getBreakingNewsWithCache(): Response<NewsResponse> {
        val breakingNews = getBreakingNews()
        if (breakingNews is Response.Success) {
            breakingNews.data.articles.map { it.generateId() }
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