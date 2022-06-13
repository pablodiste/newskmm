package com.pablodiste.android.newskmm.sqldelight

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory() {
    fun createDriver(): SqlDriver
}

/*

fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    val database = Database(driver)
    // Do more work with the database (see below).
}

 */