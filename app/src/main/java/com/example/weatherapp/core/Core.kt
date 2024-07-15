package com.example.weatherapp.core

import com.example.weatherapp.repository.city.CityService
import com.example.weatherapp.repository.weather.WeatherService
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
    fun weatherService() : WeatherService = retrofit.create(WeatherService::class.java)
    companion object {
        private const val URL = "http://api.openweathermap.org"
    }
}