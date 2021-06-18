package com.yahoo.finance.test.framework.network

import com.google.gson.annotations.SerializedName
import com.yahoo.finance.test.domain.model.Stock
import java.io.Serializable

data class YahooPriceResponse(@SerializedName("quoteSummary") val summary: QuoteSummary)

data class QuoteSummary(@SerializedName("result") val result: List<YahooPrice> = listOf())

data class YahooPrice(@SerializedName("price") val price: PriceResponse? = null)

data class PriceResponse(
    @SerializedName("symbol") val symbol: String? = null,
    @SerializedName("regularMarketChangePercent") val changePercent: Value? = null,
    @SerializedName("regularMarketChange") val change: Value? = null,
    @SerializedName("regularMarketTime") val dateTime: Long? = null,
    @SerializedName("regularMarketPrice") val price: Value? = null,
    @SerializedName("regularMarketDayHigh") val high: Value? = null,
    @SerializedName("regularMarketDayLow") val low: Value? = null,
    @SerializedName("regularMarketPreviousClose") val previousClose: Value? = null,
    @SerializedName("regularMarketOpen") val open: Value? = null,
    @SerializedName("currency") val currency: String? = null,
    @SerializedName("shortName") val tickerShortName: String? = null,
    @SerializedName("longName") val tickerLongName: String? = null,
    @SerializedName("exchangeName") val exchangeName: String? = null
)

data class Value(
    @SerializedName("raw") val raw: Double,
    @SerializedName("fmt") val fmt: String
) : Serializable

fun PriceResponse.toStock(): Stock {
    if (symbol == null) {
        throw IllegalStateException("Symbol can't be null.")
    }

    val changePercentValue = try {
        changePercent?.fmt?.replace("%", "")?.toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }

    return Stock(
        symbol,
        price?.raw ?: 0.0,
        change?.raw ?: 0.0,
        changePercentValue ?: 0.0,
        changePercent?.fmt ?: "",
        high?.raw ?: 0.0,
        low?.raw ?: 0.0,
        previousClose?.raw ?: 0.0,
        open?.raw ?: 0.0,
        dateTime ?: 0,
        currency ?: "",
        tickerShortName,
        tickerLongName,
        exchangeName
    )
}