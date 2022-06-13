package com.pablodiste.android.newskmm.di

import com.pablodiste.android.newskmm.api.NewsApi
import com.pablodiste.android.newskmm.repository.NewsRepository
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val di = DI {
    bindSingleton { NewsApi() }
    bindSingleton { NewsRepository(newsApi = instance()) }
}

val appModule = module {
    singleOf(::NewsApi)
    singleOf(::NewsRepository)
}