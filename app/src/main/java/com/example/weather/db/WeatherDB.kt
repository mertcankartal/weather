package com.example.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.models.CurrentWeatherResponse

@Database(entities = [CurrentWeatherResponse::class], version = 1)
abstract class WeatherDB : RoomDatabase() {
    abstract fun weatherDao(): WeatherDAO
}