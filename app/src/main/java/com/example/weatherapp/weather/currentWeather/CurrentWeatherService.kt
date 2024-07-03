package com.example.weatherapp.weather.currentWeather

import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherService {
    @GET("/data/2.5/weather")
    suspend fun fetch(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
        @Query("appid") appid : String = API_KEY,
        @Query("units") units : String = UNITS
    ) : CurrentWeatherData

    companion object {
        private const val API_KEY = "4240ded606dd2468bd5ed39d7a005a32"
        private const val UNITS = "metric"
    }
}