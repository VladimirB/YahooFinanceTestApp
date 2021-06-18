package com.yahoo.finance.test.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StockLocal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun stockDao(): StockDao
}