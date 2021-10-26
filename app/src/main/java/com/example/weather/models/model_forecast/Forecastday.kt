package com.example.finalproject.models.model_forecast

import com.example.weather.models.Hour

data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
)