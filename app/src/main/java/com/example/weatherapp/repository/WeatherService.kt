package com.example.weatherapp.repository

interface WeatherService {
    suspend fun currentWeatherFetch(
        latitude : Double,
        longitude : Double,
        appid : String,
        units : String
    ) : CurrentWeatherResponse
    suspend fun todayWeatherFetch(
        latitude : Double,
        longitude : Double,
        appid : String,
        units : String,
        count : Int
    ) : TodayWeatherResponse
    suspend fun futureWeatherFetch(
        latitude : Double,
        longitude : Double,
        appid : String,
        units : String
    ) : FutureWeatherResponse
}