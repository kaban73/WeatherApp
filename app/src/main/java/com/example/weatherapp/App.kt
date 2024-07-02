package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.city.CityLivaDataWrapper
import com.example.weatherapp.city.CityRepository
import com.example.weatherapp.city.CityService
import com.example.weatherapp.currentWeather.CurrentWeatherLiveDataWrapper
import com.example.weatherapp.currentWeather.CurrentWeatherRepository
import com.example.weatherapp.currentWeather.CurrentWeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    lateinit var viewModel: ViewModel
    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val cityService = retrofit.create(CityService::class.java)
        val currentWeatherService = retrofit.create(CurrentWeatherService::class.java)
        viewModel = ViewModel(
            CityRepository.Base(cityService),
            CurrentWeatherRepository.Base(currentWeatherService),
            CityLivaDataWrapper.Base(),
            CurrentWeatherLiveDataWrapper.Base()
            )
    }

}