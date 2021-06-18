package com.yahoo.finance.test.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yahoo.finance.test.app.databinding.ActivityMainBinding
import com.yahoo.finance.test.app.ui.StockRecyclerViewAdapter
import com.yahoo.finance.test.domain.model.Stock

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var stockAdapter: StockRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeRecyclerView()
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