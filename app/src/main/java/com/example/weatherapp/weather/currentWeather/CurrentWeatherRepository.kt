package com.example.weatherapp.weather.currentWeather

interface CurrentWeatherRepository {
    suspend fun load(
        lat : Double,
        lon : Double,
    ) : CurrentWeatherData

    class Base(
        private val service: CurrentWeatherService
    ) : CurrentWeatherRepository {
        override suspend fun load(
            lat: Double,
            lon: Double
        ): CurrentWeatherData {
            return service.fetch(
                lat,
                lon,
            )
        }
    }
}