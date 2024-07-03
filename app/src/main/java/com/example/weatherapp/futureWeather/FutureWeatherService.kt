package com.example.weatherapp.futureWeather

import retrofit2.http.GET
import retrofit2.http.Query

interface FutureWeatherService {
    @GET("/data/2.5/forecast")
    suspend fun fetch(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
        @Query("appid") appid : String = API_KEY,
        @Query("units") units : String = UNITS,
    ) : FutureWeatherResponse
    companion object {
        private const val API_KEY = "4240ded606dd2468bd5ed39d7a005a32"
        private const val UNITS = "metric"
    }
}