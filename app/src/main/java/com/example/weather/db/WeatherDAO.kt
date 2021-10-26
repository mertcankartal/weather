package com.example.weather.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.models.CurrentWeatherResponse

@Dao
interface WeatherDAO {

    //add new location
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: CurrentWeatherResponse)

    //get selected location
    @Query("SELECT * FROM WEATHERS")
    suspend fun fetchWeathers(): List<CurrentWeatherResponse>
}