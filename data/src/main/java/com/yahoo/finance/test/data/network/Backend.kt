package com.yahoo.finance.test.data.network

import com.yahoo.finance.test.domain.model.Stock
import io.reactivex.Single

interface Backend {

    fun getTickerDetails(ticker: String): Single<Stock>
}