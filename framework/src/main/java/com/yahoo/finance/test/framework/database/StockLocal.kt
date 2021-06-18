package com.yahoo.finance.test.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yahoo.finance.test.domain.model.Stock

@Entity(tableName = "stocks")
data class StockLocal(
    @PrimaryKey val ticker: String,
    val price: Double? = null,
    val change: Double? = null,
    val changePercent: Double? = null,
    val changePercentString: String? = null,
    val high: Double? = null,
    val low: Double? = null,
    val previousClose: Double? = null,
    val open: Double? = null,
    val dateTime: Long? = null,
    val currency: String? = null,
    val tickerShortName: String? = null,
    val tickerLongName: String? = null,
    val exchangeName: String? = null
)

fun StockLocal.toStock(): Stock {
    return Stock(
        ticker,
        price ?: 0.0,
        change ?: 0.0,
        changePercent ?: 0.0,
        changePercentString ?: "",
        high ?: 0.0,
        low ?: 0.0,
        previousClose ?: 0.0,
        open ?: 0.0,
        dateTime ?: 0,
        currency ?: "",
        tickerShortName,
        tickerLongName,
        exchangeName
    )
}

fun Stock.toStockLocal(): StockLocal {
    return StockLocal(
        symbol,
        price,
        change,
        changePercent,
        changePercentString,
        high,
        low,
        previousClose,
        open,
        dateTime,
        currency,
        tickerShortName,
        tickerLongName,
        exchangeName
    )
}
