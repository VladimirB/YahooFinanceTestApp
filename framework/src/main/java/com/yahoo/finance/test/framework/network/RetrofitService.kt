package com.yahoo.finance.test.framework.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    companion object {
        const val BASE_URL = "https://query1.finance.yahoo.com/"
    }
    
    @GET("v10/finance/quoteSummary/{code}?modules=price")
    fun getTickerPrice(@Path("code") tickerCode: String): Single<YahooPriceResponse>
}