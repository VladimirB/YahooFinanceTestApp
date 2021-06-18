package com.yahoo.finance.test.app.ui

import java.text.NumberFormat

val numberFormat: NumberFormat = NumberFormat.getNumberInstance().apply {
    maximumFractionDigits = 2
    minimumFractionDigits = 2
}