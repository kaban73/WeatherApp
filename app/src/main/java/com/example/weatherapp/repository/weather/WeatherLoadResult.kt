package com.example.weatherapp.repository.weather

import com.example.weatherapp.weatherScreen.UiState
import com.example.weatherapp.weatherScreen.WeatherLiveDataWrapper
import com.example.weatherapp.weatherScreen.CurrentWeatherData
import com.example.weatherapp.weatherScreen.FutureWeatherData
import com.example.weatherapp.weatherScreen.TodayWeatherData

interface WeatherLoadResult {
    fun show(updateWeatherLiveData : WeatherLiveDataWrapper.Update)
    data class CurrentWeatherSuccess(
        private val data : CurrentWeatherData
    ) :WeatherLoadResult {
        override fun show(updateWeatherLiveData: WeatherLiveDataWrapper.Update) {
            updateWeatherLiveData.update(UiState.CurrentWeatherDataShow(data, null))
        }
    }
    data class CurrentWeatherError(
        private val noConnection: Boolean
    ) : WeatherLoadResult {
        override fun show(updateWeatherLiveData: WeatherLiveDataWrapper.Update) {
            updateWeatherLiveData.update(UiState.CurrentWeatherDataShow(null, noConnection))
        }
    }
    data class TodayWeatherSuccess(
        private val data : List<TodayWeatherData>
    ) : WeatherLoadResult {
        override fun show(updateWeatherLiveData: WeatherLiveDataWrapper.Update) {
            updateWeatherLiveData.update(UiState.TodayWeatherDataShow(data, null))
        }
    }
    data class TodayWeatherError(
        private val noConnection: Boolean
    ) : WeatherLoadResult {
        override fun show(updateWeatherLiveData: WeatherLiveDataWrapper.Update) {
            updateWeatherLiveData.update(UiState.TodayWeatherDataShow(null ,noConnection))
        }
    }
    data class FutureWeatherSuccess(
        private val data : List<FutureWeatherData>
    ) : WeatherLoadResult {
        override fun show(updateWeatherLiveData: WeatherLiveDataWrapper.Update) {
            updateWeatherLiveData.update(UiState.FutureWeatherDataShow(data, null))
        }
    }
    data class FutureWeatherError(
        private val noConnection: Boolean
    ) : WeatherLoadResult {
        override fun show(updateWeatherLiveData: WeatherLiveDataWrapper.Update) {
            updateWeatherLiveData.update(UiState.FutureWeatherDataShow(null, noConnection))
        }
    }
}