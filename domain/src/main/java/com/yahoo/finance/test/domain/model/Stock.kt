package com.yahoo.finance.test.domain.model

data class Stock(
    val symbol: String,
    val price: Double,
    val change: Double,
    val changePercent: Double,
    val changePercentString: String,
    val high: Double,
    val low: Double,
    val previousClose: Double,
    val open: Double,
    val dateTime: Long,
    val currency: String,
    val tickerShortName: String? = null,
    val tickerLongName: String? = null,
    val exchangeName: String? = null
)