package com.example.weather.ui.home

import com.example.finalproject.models.model_current.Current
import com.example.finalproject.models.model_current.Location
import com.example.finalproject.models.model_forecast.Forecast
import com.example.weather.models.WeatherResponse

class HomeFragmentViewState(private val response: WeatherResponse) {
    fun getWeatherCurrent(): Current = response.current
    fun getWeatherForecast(): Forecast = response.forecast
    fun getWeatherLocation(): Location = response.location
}