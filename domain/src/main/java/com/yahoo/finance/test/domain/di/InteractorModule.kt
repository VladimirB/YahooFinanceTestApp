package com.yahoo.finance.test.domain.di

import com.yahoo.finance.test.domain.interactor.StockInteractor
import com.yahoo.finance.test.domain.repository.StockRepository
import com.yahoo.finance.test.domain.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun provideStockInteractor(repository: StockRepository, schedulerProvider: SchedulerProvider): StockInteractor {
        return StockInteractor(repository, schedulerProvider)
    }
}