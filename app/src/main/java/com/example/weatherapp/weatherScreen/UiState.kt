package com.example.weatherapp.weatherScreen

import java.lang.Exception

interface UiState {
    data class CurrentWeatherDataShow(
        private val data : CurrentWeatherData?,
        private val exception: Exception?
    ) : UiState
}