package com.yahoo.finance.test.app.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StockDetailsActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_TICKER = "extra_ticker"

        fun start(context: Context, ticker: String) {
            val intent = Intent(context, StockDetailsActivity::class.java)
            intent.putExtra(EXTRA_TICKER, ticker)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}