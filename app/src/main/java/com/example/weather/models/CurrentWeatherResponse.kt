package com.example.weather.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finalproject.models.model_current.Current
import com.example.finalproject.models.model_current.Location

@Entity(tableName = "WEATHERS")

data class CurrentWeatherResponse(
    @Embedded val current: Current,
    @PrimaryKey @Embedded val location: Location
)

