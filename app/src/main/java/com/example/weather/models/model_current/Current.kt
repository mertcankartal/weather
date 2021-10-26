package com.example.finalproject.models.model_current

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class Current(
    @Embedded val condition: Condition,
    @ColumnInfo(name = "feelsLikeC") val feelslike_c: Double,
    @ColumnInfo(name = "feelsLikeF") val feelslike_f: Double,
    @ColumnInfo(name = "tempC") val temp_c: Double,
    @ColumnInfo(name = "tempF") val temp_f: Double,

    )