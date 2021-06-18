package com.yahoo.finance.test.app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yahoo.finance.test.app.R
import com.yahoo.finance.test.app.databinding.RecyclerItemStockBinding
import com.yahoo.finance.test.domain.model.Stock
import java.text.NumberFormat

class StockRecyclerViewAdapter(
    private val onClickListener: ((Stock) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private class ViewHolder(
        private val binding: RecyclerItemStockBinding,
        private val onClickListener: ((Stock) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(stock: Stock) {
            binding.stockName.text = stock.symbol
            binding.companyName.text = stock.tickerShortName
            binding.price.text = numberFormat.format(stock.price)

            val change = numberFormat.format(stock.change) + " (" + stock.changePercentString + ")"
            binding.change.text = change
            val context = binding.root.context
            if (stock.change >= 0) {
                binding.change.setTextColor(ContextCompat.getColor(context, R.color.positiveTextColor))
            } else {
                binding.change.setTextColor(ContextCompat.getColor(context, R.color.negativeTextColor))
            }

            binding.root.setOnClickListener {
                onClickListener?.invoke(stock)
            }
        }
    }

    private var stocks = listOf<Stock>()

    fun setItems(stocks: List<Stock>) {
        this.stocks = stocks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: RecyclerItemStockBinding = RecyclerItemStockBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(stocks[position])
    }

    override fun getItemCount(): Int {
        return stocks.size
    }
}