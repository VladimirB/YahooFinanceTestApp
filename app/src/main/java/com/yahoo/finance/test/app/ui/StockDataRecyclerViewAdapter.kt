package com.yahoo.finance.test.app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yahoo.finance.test.app.databinding.RecyclerItemStockDataBinding

class StockDataRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private class ViewHolder(
        private val binding: RecyclerItemStockDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pair: Pair<String, String>) {
            binding.title.text = pair.first
            binding.value.text = pair.second
        }
    }

    private var items: List<Pair<String, String>> = listOf()

    fun setItems(items: List<Pair<String, String>>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RecyclerItemStockDataBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}