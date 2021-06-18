package com.yahoo.finance.test.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(context: Context) {

    private val applicationContext: Context = context.applicationContext

    @Provides
    @Singleton
    fun provideAppContext(): Context {
        return applicationContext
    }
}