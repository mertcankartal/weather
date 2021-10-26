package com.example.weather

import android.app.Application
import com.example.weather.di.dbModule
import com.example.weather.di.networkModule
import com.example.weather.di.repositoryModule
import com.example.weather.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MvvmSampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MvvmSampleApplication)
            modules(networkModule, repositoryModule, viewModelModule, dbModule)
        }


    }
}