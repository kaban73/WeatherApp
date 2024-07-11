package com.example.weatherapp.repository.city

import java.net.UnknownHostException

interface CityRepository {
    suspend fun load(
        cityName : String
    ) : CityLoadResult
    suspend fun load(
        latitude : Double,
        longitude : Double
    ) : CityLoadResult
    class Base(
        private val service: CityService
    ) : CityRepository {
        override suspend fun load(cityName: String) : CityLoadResult {
            try {
                val response = service.fetch(cityName)
                return CityLoadResult.Success(response)
            } catch (e : Exception) {
                return CityLoadResult.Error(e is UnknownHostException)
            }
        }

        override suspend fun load(latitude: Double, longitude: Double): CityLoadResult {
            try {
                val response = service.fetch(latitude, longitude)
                return CityLoadResult.Success(response)
            } catch (e : Exception) {
                return CityLoadResult.Error(e is UnknownHostException)
            }
        }
    }
}