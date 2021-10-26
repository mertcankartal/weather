package com.example.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.base.IBaseRecyclerViewItemClickListener
import com.example.weather.R
import com.example.weather.databinding.ViewpagerBinding
import com.example.weather.models.WeatherResponse

class ViewPagerAdapter(private val cityList: MutableList<WeatherResponse>) :
    RecyclerView.Adapter<ItemViewHolder>() {

    private var itemClickListener: IBaseRecyclerViewItemClickListener<WeatherResponse>? = null

    constructor(
        movieList: MutableList<WeatherResponse>,
        itemClickListener: IBaseRecyclerViewItemClickListener<WeatherResponse>
    ) : this(movieList) {
        this.itemClickListener = itemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.viewpager,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val weatherCurrent = this.cityList[position]
        holder.populate(weatherCurrent)
        holder.setOnItemClickListener(weatherCurrent, this.itemClickListener!!)
    }

    override fun getItemCount(): Int = cityList.size
}

class ItemViewHolder(private val binding: ViewpagerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun populate(weatherCurrent: WeatherResponse) {
        binding.data = weatherCurrent
        binding.executePendingBindings()
    }

    fun setOnItemClickListener(
        weather: WeatherResponse,
        itemClickListener: IBaseRecyclerViewItemClickListener<WeatherResponse>?
    ) {
        val view = binding.locationName
        view.setOnClickListener {
            itemClickListener!!.onClick(weather, it.id)
        }
    }
}