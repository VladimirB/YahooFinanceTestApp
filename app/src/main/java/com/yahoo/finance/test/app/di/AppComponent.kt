package com.yahoo.finance.test.app.di

import com.yahoo.finance.test.app.viewmodel.StockViewModel
import com.yahoo.finance.test.data.di.RepositoryModule
import com.yahoo.finance.test.domain.di.InteractorModule
import com.yahoo.finance.test.framework.di.DatabaseModule
import com.yahoo.finance.test.framework.di.NetworkModule
import com.yahoo.finance.test.framework.di.SchedulerProviderModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        InteractorModule::class,
        RepositoryModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        SchedulerProviderModule::class]
)
interface AppComponent {
    fun inject(viewModel: StockViewModel)
}