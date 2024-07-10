package com.example.weatherapp.repository.weather

import com.example.weatherapp.weatherScreen.CurrentWeatherData
import com.example.weatherapp.weatherScreen.TodayWeatherData
import java.net.UnknownHostException

interface WeatherRepository {
    suspend fun currentWeatherLoad(
        latitude : Double,
        longitude : Double
    ) : WeatherLoadResult
    suspend fun todayWeatherLoad(
        latitude : Double,
        longitude : Double
    ) : WeatherLoadResult
    class Base(
        private val service: WeatherService
    ) : WeatherRepository {
        override suspend fun currentWeatherLoad(
            latitude: Double,
            longitude: Double
        ): WeatherLoadResult {
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
                return WeatherLoadResult.CurrentWeatherSuccess(response)
            } catch (e: Exception) {
                return WeatherLoadResult.CurrentWeatherError(e is UnknownHostException)
            }
        }

        override suspend fun todayWeatherLoad(
            latitude: Double,
            longitude: Double
        ): WeatherLoadResult {
            try {
                val response = ArrayList<TodayWeatherData>()
                service.todayWeatherFetch(latitude, longitude).let {
                    it.list.forEach {
                        response.add(TodayWeatherData(it.weather.last().icon, it.date, it.main.temp))
                    }
                }
                return WeatherLoadResult.TodayWeatherSuccess(response)
            } catch (e : Exception) {
                return WeatherLoadResult.TodayWeatherError(e is UnknownHostException)
            }
        }
    }
}