package com.example.weatherapp.repository.weather

import com.example.weatherapp.weatherScreen.CurrentWeatherData
import com.example.weatherapp.weatherScreen.CurrentWeatherLiveDataWrapper
import com.example.weatherapp.weatherScreen.UiState
import java.net.UnknownHostException

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
            val exception = if (noConnection) UnknownHostException() else Exception()
            updateCurrentWeatherLiveData.update(UiState.CurrentWeatherDataShow(null, exception))
        }

    }
}