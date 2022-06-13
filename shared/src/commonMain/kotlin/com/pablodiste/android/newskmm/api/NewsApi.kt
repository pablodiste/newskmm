package com.pablodiste.android.newskmm.api

import com.pablodiste.android.newskmm.domain.NewsResponse
import com.pablodiste.android.newskmm.domain.Response
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

class NewsApi {

    private val httpClient = HttpClient() {
        install(ContentNegotiation) { json() }
        install(Logging)
    }

    suspend fun getBreakingNews(): Response<NewsResponse> {
        return try {
            val url = "https://newsapi.org/v2/top-headlines"
            val response: NewsResponse = httpClient.get(url) {
                parameter("country", "us")
                parameter("page", "1")
                parameter("apiKey", "70682c67585f47eca4394276cb6f79bb")
            }.body()
            Response.Success(response)
        } catch (ex: Exception) {
            Response.Error(ex)
        }
    }

}