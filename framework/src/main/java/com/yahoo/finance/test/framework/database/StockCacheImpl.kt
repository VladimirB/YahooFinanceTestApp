package com.yahoo.finance.test.framework.database

import com.yahoo.finance.test.data.database.StockCache
import com.yahoo.finance.test.domain.model.Stock
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class StockCacheImpl
@Inject constructor(private val dao: StockDao) : StockCache {

    override fun getAll(): Maybe<List<Stock>> {
        return dao.getAll()
            .flatMapMaybe { cachedStocks ->
                if (cachedStocks.isEmpty()) {
                    Maybe.empty()
                } else {
                    val stocks = cachedStocks.map { it.toStock() }
                    Maybe.just(stocks)
                }
            }
    }

    override fun getByTicker(ticker: String): Maybe<Stock> {
        return dao.getByTicker(ticker)
            .map { it.toStock() }
    }

    override fun save(stock: Stock): Single<Long> {
        return dao.insert(stock.toStockLocal())
    }
}