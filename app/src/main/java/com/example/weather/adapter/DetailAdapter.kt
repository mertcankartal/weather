package com.example.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.DetailRowRecyclerBinding
import com.example.weather.models.Hour

class DetailAdapter(private val hourList: List<Hour>) :
    RecyclerView.Adapter<ItemViewHolderDetail>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolderDetail {
        return ItemViewHolderDetail(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.detail_row_recycler,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolderDetail, position: Int) {
        val weatherCurrent = this.hourList[position]
        holder.populate(weatherCurrent)
    }

    override fun getItemCount(): Int = hourList.size
}

class ItemViewHolderDetail(private val binding: DetailRowRecyclerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun populate(weatherCurrent: Hour) {
        binding.detail = weatherCurrent
        binding.executePendingBindings()
    }
}