package com.example.weatherapp.repository.weather

import com.example.weatherapp.weatherScreen.CurrentWeatherData
import com.example.weatherapp.weatherScreen.CurrentWeatherLiveDataWrapper
import com.example.weatherapp.weatherScreen.UiState

interface CurrentWeatherLoadResult {
    fun show(updateCurrentWeatherLiveData : CurrentWeatherLiveDataWrapper.Update)
    data class Success(
        private val data : CurrentWeatherData
    ) : CurrentWeatherLoadResult{
        override fun show(updateCurrentWeatherLiveData: CurrentWeatherLiveDataWrapper.Update) {
            updateCurrentWeatherLiveData.update(UiState.CurrentWeatherDataShow(data, null))
        }

    }
    data class Error(
        private val noConnection : Boolean
    ) : CurrentWeatherLoadResult {
        override fun show(updateCurrentWeatherLiveData: CurrentWeatherLiveDataWrapper.Update) {
            updateCurrentWeatherLiveData.update(UiState.CurrentWeatherDataShow(null, noConnection))
        }

    }
}