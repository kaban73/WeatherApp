package com.example.weatherapp.repository.weather

import com.example.weatherapp.weatherScreen.CurrentWeatherData
import java.net.UnknownHostException

interface WeatherRepository {
    suspend fun currentWeatherLoad(
        latitude : Double,
        longitude : Double
    ) : CurrentWeatherLoadResult

    class Base(
        private val service: WeatherService
    ) : WeatherRepository {
        override suspend fun currentWeatherLoad(
            latitude: Double,
            longitude: Double
        ): CurrentWeatherLoadResult {
            try {
                val response = service.currentWeatherFetch(latitude, longitude).let {
                    val precip = if (it.rain != null)
                        Pair(it.rain.precip, "Rain")
                    else if (it.snow != null)
                        Pair(it.snow.precip, "Snow")
                    else
                        Pair(0.00, "")
                    CurrentWeatherData(
                        it.weather.last().icon,
                        it.main.temp,
                        it.wind.speed,
                        it.wind.deg,
                        precip,
                        it.date
                    )
                }
                return CurrentWeatherLoadResult.Success(response)
            } catch (e: Exception) {
                return CurrentWeatherLoadResult.Error(e is UnknownHostException)
            }
        }
    }
}