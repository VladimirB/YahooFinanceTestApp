package com.yahoo.finance.test.app.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yahoo.finance.test.app.R
import com.yahoo.finance.test.app.databinding.ActivityStockDetailsBinding
import com.yahoo.finance.test.app.model.Event
import com.yahoo.finance.test.app.viewmodel.StockViewModel
import com.yahoo.finance.test.domain.model.Stock

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

    private lateinit var viewModel: StockViewModel

    private lateinit var marketDataAdapter: StockDataRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStockDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ticker = intent.getStringExtra(EXTRA_TICKER)
            ?: throw IllegalStateException("Ticker should be specified to open this page")
        supportActionBar?.title = ticker
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initializeRecyclerView()
        initializeViewModel(ticker)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initializeRecyclerView() {
        marketDataAdapter = StockDataRecyclerViewAdapter()
        binding.recyclerView.apply {
            adapter = marketDataAdapter
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context)
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(divider)
        }
    }

    private fun initializeViewModel(ticker: String) {
        viewModel = ViewModelProvider(this)[StockViewModel::class.java]
        viewModel.stockDetails.observe(this, { event ->
            when (event) {
                is Event.Loading -> {
                }
                is Event.Success -> {
                    event.data?.let { showMarketData(it) }
                }
                is Event.Failure -> {
                    Toast.makeText(this, event.error?.message, Toast.LENGTH_LONG).show()
                }
            }
        })
        viewModel.loadStockDetails(ticker)
    }

    private fun showMarketData(stock: Stock) {
        binding.stockName.text = stock.symbol
        binding.companyName.text = stock.tickerShortName
        binding.price.text = numberFormat.format(stock.price)

        val change = numberFormat.format(stock.change) + " (" + stock.changePercentString + ")"
        binding.change.text = change
        if (stock.change > 0) {
            binding.change.setTextColor(ContextCompat.getColor(this, R.color.positiveTextColor))
        } else {
            binding.change.setTextColor(ContextCompat.getColor(this, R.color.negativeTextColor))
        }
        
        val pairs = listOf<Pair<String, String?>>(
            "Open" to numberFormat.format(stock.open),
            "High" to numberFormat.format(stock.high),
            "Low" to numberFormat.format(stock.low),
            "Previous Close" to numberFormat.format(stock.previousClose),
            "Currency" to stock.currency,
            "Exchange" to stock.exchangeName
        )
        marketDataAdapter.setItems(pairs)
    }
}