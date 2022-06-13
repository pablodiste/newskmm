package com.pablodiste.android.newskmm.domain

sealed class Response<out T> {
    class Success<out T>(val data: T) : Response<T>()
    class Error(val exception: Throwable) : Response<Nothing>()
    class Empty(): Response<Nothing>()
}