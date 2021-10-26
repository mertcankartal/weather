package com.example.weather.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.models.model_forecast.Forecast
import com.example.finalproject.models.model_forecast.Forecastday
import com.example.weather.Result
import com.example.weather.models.WeatherResponse
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    val onForecastFetched = MutableLiveData<HomeFragmentViewState>()
    val onError = MutableLiveData<Unit>()
    val searchResult = MutableLiveData<AutoSearchStateModel>()

    fun searchedWeather(city: String) {
        viewModelScope.launch {
            when (val searchedResponse = weatherRepository.getWeatherSearched(city)) {
                is Result.Success -> {
                    searchResult.value = AutoSearchStateModel(searchedResponse.data!!)
                }
                is Result.Error -> {
                    onError.value = Unit
                }
            }
        }
    }

    fun forecastWeather(city: String) {
        viewModelScope.launch {
            val response = weatherRepository.getWeatherForecast(city)
            when (response) {
                is Result.Success -> {
                    onForecastFetched.value = HomeFragmentViewState(response.data!!)
                }
                is Result.Error -> onError.value = Unit
            }
        }
    }

    fun prepareSelectedWeather() {
        viewModelScope.launch {
            val list = listOf<Forecastday>()
            weatherRepository.getWeatherAsync().forEach {
                onForecastFetched.value = HomeFragmentViewState(
                    WeatherResponse(
                        it.current,
                        Forecast(list), it.location
                    )
                )
            }
        }
    }


}