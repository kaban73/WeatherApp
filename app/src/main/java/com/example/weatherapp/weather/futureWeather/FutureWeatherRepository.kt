package com.example.weatherapp.weather.futureWeather

interface FutureWeatherRepository {
    suspend fun load(
        lat : Double,
        lon : Double
    ) : FutureWeatherResponse
    class Base(
        private val service: FutureWeatherService
    ) : FutureWeatherRepository {
        override suspend fun load(lat: Double, lon: Double): FutureWeatherResponse {
            return service.fetch(
                lat,
                lon
            )
        }
    }
}