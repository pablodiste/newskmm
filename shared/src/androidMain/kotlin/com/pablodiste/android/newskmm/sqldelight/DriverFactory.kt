package com.pablodiste.android.newskmm.sqldelight

import android.content.Context
import com.pablodiste.newskmm.NewsDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(NewsDatabase.Schema, context, "test.db")
    }
}
