package com.example.finalproject.models.model_forecast

import androidx.room.ColumnInfo


data class Forecast(
    @ColumnInfo(name="forecast") val forecastday: List<Forecastday>
)