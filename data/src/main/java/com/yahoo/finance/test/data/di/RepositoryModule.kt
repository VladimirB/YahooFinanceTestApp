package com.yahoo.finance.test.data.di

import com.yahoo.finance.test.data.database.StockCache
import com.yahoo.finance.test.data.network.Backend
import com.yahoo.finance.test.data.repository.StockRepositoryImpl
import com.yahoo.finance.test.domain.repository.StockRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideStockRepository(backend: Backend, cache: StockCache): StockRepository {
        return StockRepositoryImpl(backend, cache)
    }
}