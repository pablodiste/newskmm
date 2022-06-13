package com.pablodiste.android.newskmm.sqldelight

import android.content.Context
import com.pablodiste.android.newskmm.appContext
import com.pablodiste.newskmm.NewsDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(NewsDatabase.Schema, appContext, "test.db")
    }
}
