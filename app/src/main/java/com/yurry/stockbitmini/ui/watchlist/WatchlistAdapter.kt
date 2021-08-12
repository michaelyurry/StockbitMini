package com.yurry.stockbitmini.ui.watchlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yurry.stockbitmini.R
import com.yurry.stockbitmini.Util
import com.yurry.stockbitmini.data.remote.model.CryptoItem
import com.yurry.stockbitmini.databinding.CryptoItemBinding

class WatchlistAdapter: PagedListAdapter<CryptoItem, WatchlistAdapter.CryptoViewHolder>(
    object : DiffUtil.ItemCallback<CryptoItem>(){
        override fun areItemsTheSame(oldItem: CryptoItem, newItem: CryptoItem): Boolean {
            return oldItem.coinInfo?.id == newItem.coinInfo?.id
        }

        override fun areContentsTheSame(oldItem: CryptoItem, newItem: CryptoItem): Boolean {
            return oldItem == newItem
        }

    }) {

        class CryptoViewHolder(val binding: CryptoItemBinding): RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
            val binding = CryptoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CryptoViewHolder(binding)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
            val item = getItem(position) as CryptoItem
            holder.binding.nameView.text = item.coinInfo?.name
            holder.binding.fullnameView.text = item.coinInfo?.fullName
            holder.binding.priceView.text = Util.printDecimalFormat(item.rawInfo?.usdCurrencyItem?.price?:0.0)
            holder.binding.changePerHourView.apply {
                val value = item.rawInfo?.usdCurrencyItem?.changeHour?:0.0
                text = if(value > 0) "+${Util.printDecimalFormat(value)}" else Util.printDecimalFormat(value)
                when {
                    value < 0 -> {
                        setTextColor(ContextCompat.getColorStateList(context, R.color.red))
                    }
                    value == 0.0 -> {
                        setTextColor(ContextCompat.getColorStateList(context, R.color.black))
                    }
                    else -> {
                        setTextColor(ContextCompat.getColorStateList(context, R.color.dark_green))
                    }
                }
            }
            holder.binding.changePerHourInPercentView.apply {
                val value = item.rawInfo?.usdCurrencyItem?.changeHourPercent?:0.0
                text =  "("+if(value > 0) "+${Util.printDecimalFormat(value)})" else Util.printDecimalFormat(value)+")"
                when {
                    value < 0.0 -> {
                        setTextColor(ContextCompat.getColorStateList(context, R.color.red))
                    }
                    value == 0.0 -> {
                        setTextColor(ContextCompat.getColorStateList(context, R.color.black))
                    }
                    else -> {
                        setTextColor(ContextCompat.getColorStateList(context, R.color.dark_green))
                    }
                }
            }
        }
}