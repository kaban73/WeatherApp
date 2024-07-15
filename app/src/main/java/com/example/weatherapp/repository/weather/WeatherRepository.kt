package com.example.weatherapp.repository.weather

import com.example.weatherapp.core.LoadResult
import com.example.weatherapp.weatherScreen.current.CurrentWeatherData
import com.example.weatherapp.weatherScreen.today.TodayWeatherData
import java.net.UnknownHostException

interface WeatherRepository {
    suspend fun currentWeatherLoad(
        latitude : Double,
        longitude : Double
    ) : LoadResult
    suspend fun todayWeatherLoad(
        latitude : Double,
        longitude : Double
    ) : LoadResult
    suspend fun futureWeatherLoad(
        latitude : Double,
        longitude : Double
    ) : LoadResult
    class Base(
        private val service: WeatherService
    ) : WeatherRepository {
        override suspend fun currentWeatherLoad(
            latitude: Double,
            longitude: Double
        ): LoadResult {
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
                return LoadResult.CurrentWeatherSuccess(response)
            } catch (e: Exception) {
                return LoadResult.CurrentWeatherError(e is UnknownHostException)
            }
        }

        override suspend fun todayWeatherLoad(
            latitude: Double,
            longitude: Double
        ): LoadResult {
            try {
                val response = ArrayList<TodayWeatherData>()
                service.todayWeatherFetch(latitude, longitude).let {
                    it.list.forEach {
                        response.add(TodayWeatherData(it.weather.last().icon, it.date, it.main.temp))
                    }
                }
                return LoadResult.TodayWeatherSuccess(response)
            } catch (e : Exception) {
                return LoadResult.TodayWeatherError(e is UnknownHostException)
            }
        }

        override suspend fun futureWeatherLoad(
            latitude: Double,
            longitude: Double
        ): LoadResult {
            try {
                val response = service.futureWeatherFetch(latitude, longitude)
                val result = FutureWeatherCalc.Base(response).list()
                return LoadResult.FutureWeatherSuccess(result)
            } catch (e : Exception) {
                return LoadResult.FutureWeatherError(e is UnknownHostException)
            }
        }
    }
}