package com.example.weatherapp.weatherScreen

interface UiState {
    data class CurrentWeatherDataShow(
        private val data : CurrentWeatherData?,
        private val noConnection: Boolean?
    ) : UiState
}