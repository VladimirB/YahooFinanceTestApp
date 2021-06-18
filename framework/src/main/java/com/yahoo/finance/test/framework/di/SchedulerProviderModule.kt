package com.yahoo.finance.test.framework.di

import com.yahoo.finance.test.domain.scheduler.SchedulerProvider
import com.yahoo.finance.test.framework.scheduler.SchedulerProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface SchedulerProviderModule {

    @Binds
    @Singleton
    fun provideSchedulerProvider(provider: SchedulerProviderImpl): SchedulerProvider
}