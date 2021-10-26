package com.example.weather.ui.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.finalproject.base.BaseFragment
import com.example.finalproject.base.IBaseRecyclerViewItemClickListener
import com.example.weather.R
import com.example.weather.adapter.AutoCompleteTextAdapter
import com.example.weather.adapter.ViewPagerAdapter
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.models.WeatherResponse
import com.example.weather.util.gone
import com.example.weather.util.toast
import com.example.weather.util.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeFragmentViewModel, FragmentHomeBinding>() {

    private val cityList = mutableListOf<WeatherResponse>()

    override val myViewModel: HomeFragmentViewModel by viewModel()

    override fun getLayoutID(): Int = R.layout.fragment_home

    override fun observeLiveData() {
        myViewModel.searchResult.observe(this, {
            var adapter = AutoCompleteTextAdapter(
                requireContext(),
                R.id.autoCompleteItem,
                it.getAllResults()
            )
            adapter.setOnClickListener(object : IBaseRecyclerViewItemClickListener<String> {
                override fun onClick(clickedObject: String, id: Int) {
                    myViewModel.forecastWeather(clickedObject)
                }
            })
            dataBinding.autoCompleteText.setAdapter(adapter)
        })

        myViewModel.onForecastFetched.observe(this, {
            dataBinding.executePendingBindings()
            if (!addLocationListController(it.getWeatherLocation().region)) {
                cityList.add(
                    WeatherResponse(
                        it.getWeatherCurrent(),
                        it.getWeatherForecast(),
                        it.getWeatherLocation()
                    )
                )
                dataBinding.viewPager.adapter?.notifyItemInserted(cityList.size - 1)
            }

            if (cityList.size == 0) {
                dataBinding.textEmpty.visible()
            } else {
                dataBinding.textEmpty.gone()
            }
        })
    }

    override fun prepareView() {

        myViewModel.prepareSelectedWeather()

        dataBinding.viewPager.adapter = ViewPagerAdapter(cityList,
            object : IBaseRecyclerViewItemClickListener<WeatherResponse> {
                override fun onClick(clickedObject: WeatherResponse, id: Int) {
                    when (id) {
                        R.id.locationName -> {
                            val bundle = bundleOf("locationName" to clickedObject.location.region)
                            if (checkForInternet(requireContext())) {
                                findNavController().navigate(
                                    R.id.action_homeFragment_to_detailFragment,
                                    bundle
                                )
                            } else {
                              toast("No internet connection for detail")
                            }
                        }
                    }
                }

            })

        dataBinding.autoCompleteText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0!!.length >= 3) {
                    myViewModel.searchedWeather(p0.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                //("Not yet implemented")
            }
        })


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (checkForInternet(requireContext())) {
            toast("Internet is available")
        } else {
            toast("No Internet Connection")
        }
    }

    private fun checkForInternet(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    private fun addLocationListController(region: String): Boolean {
        var isInList = false
        for (item in cityList) {
            if (region.contains(item.location.region)) {
                isInList = true
                break
            }
        }
        return isInList
    }


}