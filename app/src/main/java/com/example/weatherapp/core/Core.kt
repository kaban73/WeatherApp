package com.example.weatherapp.core

import com.example.weatherapp.city.repository.CityService
import com.example.weatherapp.weather.currentWeather.CurrentWeatherService
import com.example.weatherapp.weather.futureWeather.FutureWeatherService
import com.example.weatherapp.weather.todayWeather.TodayWeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Core {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun cityService() : CityService = retrofit.create(CityService::class.java)
    fun currentWeatherService() : CurrentWeatherService = retrofit.create(CurrentWeatherService::class.java)
    fun todayWeatherService() : TodayWeatherService = retrofit.create(TodayWeatherService::class.java)
    fun futureWeatherService() : FutureWeatherService = retrofit.create(FutureWeatherService::class.java)
    companion object {
        private const val URL = "http://api.openweathermap.org"
    }
}