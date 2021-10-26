package com.example.weather.models

import androidx.room.Entity
import com.example.finalproject.models.model_current.Current
import com.example.finalproject.models.model_current.Location
import com.example.finalproject.models.model_forecast.Forecast

data class WeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)
