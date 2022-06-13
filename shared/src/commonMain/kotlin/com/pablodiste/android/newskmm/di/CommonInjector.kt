package com.pablodiste.android.newskmm.di

import com.pablodiste.android.newskmm.api.NewsApi
import com.pablodiste.android.newskmm.repository.NewsRepository
import com.pablodiste.android.newskmm.sqldelight.DriverFactory
import com.pablodiste.newskmm.NewsDatabase
import com.squareup.sqldelight.db.SqlDriver
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/*
val di = DI {
    bindSingleton { NewsApi() }
    bindSingleton { NewsRepository(newsApi = instance()) }
}
*/

// Koin implementation
val appModule = module {
    single { NewsDatabase(get<DriverFactory>().createDriver()) }
    singleOf(::NewsApi)
    singleOf(::NewsRepository)
    singleOf(::DriverFactory)
}