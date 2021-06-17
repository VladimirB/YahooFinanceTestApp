package com.yahoo.finance.test.domain.repository

import com.yahoo.finance.test.domain.model.Stock
import io.reactivex.Single

interface StockRepository {

    fun getStocks(tickers: List<String>): Single<List<Stock>>

    fun getStockDetails(ticker: String): Single<Stock>
}