package com.yahoo.finance.test.app.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.yahoo.finance.test.app.databinding.ActivityStockDetailsBinding

class StockDetailsActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_TICKER = "extra_ticker"

        fun start(context: Context, ticker: String) {
            val intent = Intent(context, StockDetailsActivity::class.java)
            intent.putExtra(EXTRA_TICKER, ticker)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityStockDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ticker = intent.getStringExtra(EXTRA_TICKER)
        supportActionBar?.title = ticker
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}