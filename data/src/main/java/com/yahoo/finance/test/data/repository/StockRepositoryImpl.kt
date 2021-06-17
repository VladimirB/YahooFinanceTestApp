package com.yahoo.finance.test.data.repository

import com.yahoo.finance.test.data.database.StockCache
import com.yahoo.finance.test.data.network.Backend
import com.yahoo.finance.test.domain.model.Stock
import com.yahoo.finance.test.domain.repository.StockRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class StockRepositoryImpl
@Inject constructor(
    private val backend: Backend,
    private val cache: StockCache
) : StockRepository {

    override fun getStocks(tickers: List<String>): Single<List<Stock>> {
        return cache.getAll()
            .switchIfEmpty(loadStocksFromBackend(tickers))
    }

    override fun getStockDetails(ticker: String): Single<Stock> {
        TODO("Not yet implemented")
    }

    private fun loadStocksFromBackend(tickers: List<String>): Single<List<Stock>> {
        return Observable.fromIterable(tickers)
            .flatMapSingle { ticker -> backend.getTickerDetails(ticker) }
            .flatMapSingle { stock -> saveStockInCache(stock) }
            .toList()
    }

    private fun saveStockInCache(stock: Stock): Single<Stock> {
        return cache.save(stock)
            .map { stock }
    }
}