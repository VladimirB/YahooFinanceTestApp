package com.yahoo.finance.test.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yahoo.finance.test.app.model.Event
import com.yahoo.finance.test.domain.interactor.StockInteractor
import com.yahoo.finance.test.domain.model.Stock
import javax.inject.Inject

class StockViewModel : BaseViewModel() {

    @Inject
    lateinit var stockInteractor: StockInteractor

    private val _stocks: MutableLiveData<Event<List<Stock>>> = MutableLiveData()
    val stocks: LiveData<Event<List<Stock>>> = _stocks

    private val _stockDetails: MutableLiveData<Event<Stock>> = MutableLiveData()
    val stockDetails: LiveData<Event<Stock>> = _stockDetails

    fun loadStocks(tickers: List<String>) {
        disposables.add(stockInteractor.getStocks(tickers)
            .doOnSubscribe { _stocks.value = Event.Loading() }
            .subscribe({
                _stocks.value = Event.Success(it)
            }, {
                _stocks.value = Event.Failure(it)
            })
        )
    }

    fun loadStockDetails(ticker: String) {
        disposables.add(stockInteractor.getStockDetails(ticker)
            .doOnSubscribe { _stockDetails.value = Event.Loading() }
            .subscribe({
                _stockDetails.value = Event.Success(it)
            }, {
                _stockDetails.value = Event.Failure(it)
            })
        )
    }
}