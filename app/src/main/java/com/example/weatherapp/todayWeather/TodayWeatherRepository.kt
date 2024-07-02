package com.example.weatherapp.todayWeather

interface TodayWeatherRepository {
    suspend fun load(
        lat : Double,
        lon : Double
    ) : TodayWeatherResponse
    class Base(
        private val service: TodayWeatherService
    ) : TodayWeatherRepository {
        override suspend fun load(lat: Double, lon: Double): TodayWeatherResponse {
            return service.fetch(lat, lon)
        }
    }
}