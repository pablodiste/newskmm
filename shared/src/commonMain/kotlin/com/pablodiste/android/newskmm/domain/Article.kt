package com.pablodiste.android.newskmm.domain

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    var id : Int? = null,
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
) {
    fun generateId() {
        id = url.hashCode()
    }
    /*
    val formattedPublishedAt : String get() {
        publishedAt?.let {
            return DateUtil.changeDateFormat(publishedAt)
        }
        return ""
    }
     */
}