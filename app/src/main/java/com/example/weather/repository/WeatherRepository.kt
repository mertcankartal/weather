package com.example.weather.repository

import com.example.finalproject.models.response.SearchAutoCompleteResponse
import com.example.weather.Result
import com.example.weather.db.WeatherDAO
import com.example.weather.models.CurrentWeatherResponse
import com.example.weather.models.WeatherResponse
import com.example.weather.service.WeatherAPI

class WeatherRepository(private val api: WeatherAPI, private val dao: WeatherDAO) {

    private val key = "25b1cc1d992347bcb4d122025211510"

    //searched location
    suspend fun getWeatherSearched(city: String): Result<SearchAutoCompleteResponse> {
        val response = SearchAutoCompleteResponse(api.getSearched(key, city))
        return Result.Success(response)
    }

    //current
    suspend fun getWeatherCurrent(city: String): Result<WeatherResponse> {
        val response = api.getCurrent(key, city)
        return if (response != null) {
            Result.Success(response)
        } else {
            Result.Error("Error !!")
        }
    }

    //forecast
    suspend fun getWeatherForecast(city: String): Result<WeatherResponse> {
        val response = api.getForecast(key, city)
        return if (response != null) {
            insertDataAsync(CurrentWeatherResponse(response.current, response.location))
            Result.Success(response)
        } else {
            Result.Error("Error !!")
        }
    }

    //add new location room
    suspend fun insertDataAsync(weatherResponse: CurrentWeatherResponse) {
        dao.insertWeather(weatherResponse)
    }

    //get selected location from room
    suspend fun getWeatherAsync(): List<CurrentWeatherResponse> {
        return dao.fetchWeathers()
    }


}