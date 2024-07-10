package com.example.weatherapp.weatherScreen

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
}