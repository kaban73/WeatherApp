package com.example.weatherapp.core

import com.example.weatherapp.repository.city.CityResponse
import com.example.weatherapp.weatherScreen.CurrentWeatherData
import com.example.weatherapp.weatherScreen.FutureWeatherData
import com.example.weatherapp.weatherScreen.TodayWeatherData

interface UiState {
    data class CurrentWeatherDataShow(
        private val data : CurrentWeatherData?,
        private val noConnection: Boolean?
    ) : UiState
    data class TodayWeatherDataShow(
        private val data: List<TodayWeatherData>?,
        private val noConnection: Boolean?
    ) : UiState
    data class FutureWeatherDataShow(
        private val data : List<FutureWeatherData>?,
        private val noConnection: Boolean?
    ) : UiState
    data class CitiesListDataShow(
        private val data: List<CityResponse>?,
        private val noConnection: Boolean?
    ) : UiState
    data class CityGeoDataShow(
        private val data: CityResponse?,
        private val noConnection: Boolean?
    ) : UiState
}