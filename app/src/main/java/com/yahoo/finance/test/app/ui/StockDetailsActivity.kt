package com.yahoo.finance.test.app.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yahoo.finance.test.app.databinding.ActivityStockDetailsBinding
import com.yahoo.finance.test.app.model.Event
import com.yahoo.finance.test.app.viewmodel.StockViewModel

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

                }
                is Event.Failure -> {
                    Toast.makeText(this, event.error?.message, Toast.LENGTH_LONG).show()
                }
            }
        })
        viewModel.loadStockDetails(ticker)
    }
}