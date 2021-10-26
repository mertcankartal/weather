package com.example.weather.service

import com.example.finalproject.models.model_search.WeatherSearch
import com.example.weather.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    //all requests -> search,forecast,current

    @GET("search.json")
    suspend fun getSearched(@Query("key") key: String, @Query("q") city: String): WeatherSearch

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("days") day: Int = 1,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): WeatherResponse

    @GET("current.json")
    suspend fun getCurrent(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("aqi") aqi: String = "no"
    ): WeatherResponse

}
