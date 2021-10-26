package com.example.weather.models

import com.example.finalproject.models.model_current.Current
import com.example.finalproject.models.model_current.Location

data class WeatherCurrent(
    val current: Current,
    val location: Location
)