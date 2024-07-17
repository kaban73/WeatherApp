package com.example.weatherapp.repository

import com.example.weatherapp.core.LoadResult
import com.example.weatherapp.repository.city.CityRepository
import com.example.weatherapp.repository.weather.WeatherRepository

interface FakeWeatherRepository : WeatherRepository {
    fun expectResult(results : Map<String, LoadResult>)
    class Base : FakeWeatherRepository {
        private var results = mapOf<String, LoadResult>()
        override fun expectResult(results : Map<String, LoadResult>) {
            this.results = results
        }

        override suspend fun currentWeatherLoad(
            latitude: Double,
            longitude: Double
        ): LoadResult {
            return results["current"]!!
        }

        override suspend fun todayWeatherLoad(latitude: Double, longitude: Double): LoadResult {
            return results["today"]!!
        }

        override suspend fun futureWeatherLoad(
            latitude: Double,
            longitude: Double
        ): LoadResult {
            return results["future"]!!
        }
    }
}
interface FakeCityRepository : CityRepository {
    fun expectResult(result : LoadResult)
    class Base : FakeCityRepository {
        private lateinit var result: LoadResult
        override fun expectResult(result: LoadResult) {
            this.result = result
        }

        override suspend fun load(cityName: String): LoadResult {
            return result
        }

        override suspend fun load(latitude: Double, longitude: Double): LoadResult {
            return result
        }

    }
}