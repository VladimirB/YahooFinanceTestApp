package com.yahoo.finance.test.app.viewmodel

import androidx.lifecycle.ViewModel
import com.yahoo.finance.test.app.di.Injector
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()

    init { inject() }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    private fun inject() {
        when (this) {
            is StockViewModel -> Injector.appComponent.inject(this)
        }
    }
}