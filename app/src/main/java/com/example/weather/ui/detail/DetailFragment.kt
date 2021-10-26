package com.example.weather.ui.detail

import com.example.finalproject.base.BaseFragment
import com.example.weather.R
import com.example.weather.adapter.DetailAdapter
import com.example.weather.databinding.FragmentDetailBinding
import com.example.weather.ui.home.HomeFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<HomeFragmentViewModel, FragmentDetailBinding>() {


    override val myViewModel: HomeFragmentViewModel by viewModel()

    override fun getLayoutID(): Int = R.layout.fragment_detail

    override fun observeLiveData() {

        //get selected location's details
        myViewModel.forecastWeather(arguments?.get("locationName").toString())
        myViewModel.onForecastFetched.observe(this, {
            val adapter = DetailAdapter(it.getWeatherForecast().forecastday[0].hour)
            dataBinding.detailRecyclerView.adapter = adapter

        })
    }

    override fun prepareView() {
    }

}