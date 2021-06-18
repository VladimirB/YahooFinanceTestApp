package com.yahoo.finance.test.framework.di

import android.content.Context
import androidx.room.Room
import com.yahoo.finance.test.data.database.StockCache
import com.yahoo.finance.test.framework.database.AppDatabase
import com.yahoo.finance.test.framework.database.StockCacheImpl
import com.yahoo.finance.test.framework.database.StockDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideStockCache(dao: StockDao): StockCache {
        return StockCacheImpl(dao)
    }
    
    @Provides
    @Singleton
    fun provideStockDao(database: AppDatabase): StockDao {
        return database.stockDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "stock-db"
        ).build()
    }
}