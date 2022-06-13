package com.pablodiste.android.newskmm.domain

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)