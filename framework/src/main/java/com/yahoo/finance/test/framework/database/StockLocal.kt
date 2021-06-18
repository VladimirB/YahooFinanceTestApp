package com.yahoo.finance.test.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey

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
