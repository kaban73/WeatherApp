package com.example.weatherapp.repository.city

import com.example.weatherapp.core.LoadResult
import java.net.UnknownHostException

interface CityRepository {
    suspend fun load(
        cityName : String
    ) : LoadResult
    suspend fun load(
        latitude : Double,
        longitude : Double
    ) : LoadResult
    class Base(
        private val service: CityService
    ) : CityRepository {
        override suspend fun load(cityName: String) : LoadResult {
            try {
                val response = service.fetch(cityName)
                return LoadResult.CityNameSuccess(response)
            } catch (e : Exception) {
                return LoadResult.CityNameError(e is UnknownHostException)
            }
        }

        override suspend fun load(latitude: Double, longitude: Double): LoadResult {
            try {
                val response = service.fetch(latitude, longitude).first()
                return LoadResult.CityGeoSuccess(response)
            } catch (e : Exception) {
                return LoadResult.CityGeoError(e is UnknownHostException)
            }
        }
    }
}