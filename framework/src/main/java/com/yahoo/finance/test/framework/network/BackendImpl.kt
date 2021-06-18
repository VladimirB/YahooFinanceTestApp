package com.yahoo.finance.test.framework.network

import com.yahoo.finance.test.data.network.Backend
import com.yahoo.finance.test.domain.model.Stock
import io.reactivex.Single
import javax.inject.Inject

class BackendImpl
@Inject constructor(private val service: RetrofitService) : Backend {

    override fun getTickerDetails(ticker: String): Single<Stock> {
        return service.getTickerPrice(ticker)
            .map { response ->
                if (response.summary.result.isNotEmpty()) {
                    val price = response.summary.result[0].price
                    if (price != null) {
                        return@map price.toStock()
                    } else {
                        throw IllegalStateException("Price response not found")
                    }
                } else throw IllegalStateException("Price response not found for symbol: $ticker")
            }
    }
}