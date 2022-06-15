package com.pablodiste.android.newskmm.domain

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val articles: List<Article>,
    val status: String? = null,
    val totalResults: Int? = null
)