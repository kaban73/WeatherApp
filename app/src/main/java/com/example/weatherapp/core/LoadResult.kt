package com.example.weatherapp.core

import com.example.weatherapp.repository.city.CityResponse
import com.example.weatherapp.weatherScreen.current.CurrentWeatherData
import com.example.weatherapp.weatherScreen.future.FutureWeatherData
import com.example.weatherapp.weatherScreen.today.TodayWeatherData

interface LoadResult {
    fun show(updateLiveData: LiveDataWrapper.Update)
    data class CityNameSuccess(
        private val data : List<CityResponse>
    ) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.CitiesListDataShow(data, null))
        }
    }
    data class CityNameError(
        private val noConnection: Boolean
    ) :LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.CitiesListDataShow(null, noConnection))
        }
    }
    data class CityGeoSuccess(
        private val data : CityResponse
    ) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.CityGeoDataShow(data, null))
        }
    }
    data class CityGeoError(
        private val noConnection: Boolean
    ) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.CityGeoDataShow(null, noConnection))
        }
    }
    data class CurrentWeatherSuccess(
        private val data : CurrentWeatherData
    ) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.CurrentWeatherDataShow(data, null))
        }
    }
    data class CurrentWeatherError(
        private val noConnection: Boolean
    ) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.CurrentWeatherDataShow(null, noConnection))
        }
    }
    data class TodayWeatherSuccess(
        private val data : List<TodayWeatherData>
    ) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.TodayWeatherDataShow(data, null))
        }
    }
    data class TodayWeatherError(
        private val noConnection: Boolean
    ) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.TodayWeatherDataShow(null ,noConnection))
        }
    }
    data class FutureWeatherSuccess(
        private val data : List<FutureWeatherData>
    ) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.FutureWeatherDataShow(data, null))
        }
    }
    data class FutureWeatherError(
        private val noConnection: Boolean
    ) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.FutureWeatherDataShow(null, noConnection))
        }
    }
}