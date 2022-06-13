package com.pablodiste.android.newskmm.sqldelight

import com.pablodiste.newskmm.NewsDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(NewsDatabase.Schema, "test.db")
    }
}