package com.yahoo.finance.test.app.di

import android.content.Context

object Injector {

    lateinit var appComponent: AppComponent
        private set

    fun install(context: Context) {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build()
    }
}