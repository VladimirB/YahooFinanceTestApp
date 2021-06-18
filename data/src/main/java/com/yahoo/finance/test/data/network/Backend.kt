package com.yahoo.finance.test.data.network

import com.yahoo.finance.test.domain.model.Stock
import io.reactivex.Single

interface Backend {

    companion object {
        const val BASE_URL = "https://query1.finance.yahoo.com/"
    }

    fun getTickerDetails(ticker: String): Single<Stock>
}