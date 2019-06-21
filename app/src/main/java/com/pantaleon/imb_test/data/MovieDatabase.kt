package com.pantaleon.imb_test.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pantaleon.imb_test.data.model.Movie

@Database(entities = [Movie::class], exportSchema = false, version = 2)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}