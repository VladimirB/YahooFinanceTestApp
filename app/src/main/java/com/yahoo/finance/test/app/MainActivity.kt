package com.yahoo.finance.test.app

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yahoo.finance.test.app.databinding.ActivityMainBinding
import com.yahoo.finance.test.app.di.Injector
import com.yahoo.finance.test.app.model.Event
import com.yahoo.finance.test.app.ui.StockRecyclerViewAdapter
import com.yahoo.finance.test.app.viewmodel.StockViewModel
import com.yahoo.finance.test.domain.model.Stock

private val testTickers = listOf("GOOG", "MSFT", "TWTR", "NVDA", "PYPL")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: StockViewModel

    private lateinit var stockAdapter: StockRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Injector.install(this)

        initializeViewModel()
        initializeRecyclerView()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this)[StockViewModel::class.java]
        viewModel.stocks.observe(this, { event ->
            val visibility = if (event is Event.Loading) View.VISIBLE else View.GONE
            binding.progressBar.visibility = visibility

            when (event) {
                is Event.Success -> {
                    event.data?.let { stockAdapter.setItems(it) }
                }
                is Event.Failure -> {
                    Toast.makeText(this, event.error?.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        })

        viewModel.loadStocks(testTickers)
    }

    private fun initializeRecyclerView() {
        stockAdapter = StockRecyclerViewAdapter { stock -> onStockClicked(stock) }
        binding.recyclerView.apply {
            adapter = stockAdapter
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context)
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(divider)
        }
    }

    private fun onStockClicked(stock: Stock) {

    }
}