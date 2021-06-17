package com.yahoo.finance.test.domain.interactor

import com.yahoo.finance.test.domain.model.Stock
import com.yahoo.finance.test.domain.repository.StockRepository
import com.yahoo.finance.test.domain.scheduler.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class StockInteractor
@Inject constructor(
    private val stockRepository: StockRepository,
    schedulerProvider: SchedulerProvider
) : Interactor(schedulerProvider) {

    fun getStocks(tickers: List<String>): Single<List<Stock>> {
        return if (tickers.isEmpty()) {
            Single.just(listOf())
        } else {
            stockRepository.getStocks(tickers)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        }
    }

    fun getStockDetails(ticker: String): Single<Stock> {
        return if (ticker.isEmpty()) {
            Single.error(IllegalStateException("Please, provide a ticker"))
        } else {
            stockRepository.getStockDetails(ticker)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        }
    }
}