package com.pablodiste.android.newskmm.android

import android.app.Application
import com.pablodiste.android.newskmm.appContext
import com.pablodiste.android.newskmm.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(listOf(appModule))
        }
    }
}