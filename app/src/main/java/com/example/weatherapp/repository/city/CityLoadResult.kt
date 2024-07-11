package com.example.weatherapp.repository.city

interface CityLoadResult {
    data class Success(
        private val data : List<CityResponse>
    ) : CityLoadResult
    data class Error(
        private val noConnection : Boolean
    ) : CityLoadResult
}