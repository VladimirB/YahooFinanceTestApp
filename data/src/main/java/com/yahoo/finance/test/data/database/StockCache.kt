package com.yahoo.finance.test.data.database

import com.yahoo.finance.test.domain.model.Stock
import io.reactivex.Maybe
import io.reactivex.Single

interface StockCache {

    fun getAll(): Maybe<List<Stock>>

    fun save(stock: Stock): Single<Long>
}