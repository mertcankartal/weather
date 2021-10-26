package com.example.weather.di

import com.example.weather.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { WeatherRepository(get(), get()) }
}