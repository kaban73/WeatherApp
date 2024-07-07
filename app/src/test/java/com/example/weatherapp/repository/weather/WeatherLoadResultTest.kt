package com.example.weatherapp.repository.weather

import com.example.weatherapp.weather.FakeCurrentWeatherLiveDataWrapper
import com.example.weatherapp.weatherScreen.CurrentWeatherData
import com.example.weatherapp.weatherScreen.CurrentWeatherLiveDataWrapper
import com.example.weatherapp.weatherScreen.UiState
import org.junit.Test

class WeatherLoadResultTest {
    @Test
    fun test_success() {
        val currentWeatherLoadResult = CurrentWeatherLoadResult.Success(
            data = CurrentWeatherData(
                icon = "icon",
                degrees = 0.0,
                windSpeed = 0.0,
                windDeg = 0,
                precip = Pair(0.0, ""),
                date = 0L
            )
        )
        val currentWeatherLiveDataWrapper = FakeCurrentWeatherLiveDataWrapper.Base()
        val currentWeatherLiveDataWrapperUpdate : CurrentWeatherLiveDataWrapper.Update = currentWeatherLiveDataWrapper
        currentWeatherLoadResult.show(
            updateCurrentWeatherLiveData = currentWeatherLiveDataWrapperUpdate
        )
        currentWeatherLiveDataWrapper.checkUpdateCalls(listOf(
            UiState.CurrentWeatherDataShow(
                data = CurrentWeatherData(
                    icon = "icon",
                    degrees = 0.0,
                    windSpeed = 0.0,
                    windDeg = 0,
                    precip = Pair(0.0, ""),
                    date = 0L
                ),
                noConnection = null
            )
        ))
    }
    @Test
    fun test_no_connection() {
        val currentWeatherLoadResult = CurrentWeatherLoadResult.Error(
            noConnection = true
        )
        val fakeCurrentWeatherLiveDataWrapper = FakeCurrentWeatherLiveDataWrapper.Base()
        val currentWeatherLiveDataWrapperUpdate : CurrentWeatherLiveDataWrapper.Update = fakeCurrentWeatherLiveDataWrapper
        currentWeatherLoadResult.show(
            updateCurrentWeatherLiveData = currentWeatherLiveDataWrapperUpdate
        )
        fakeCurrentWeatherLiveDataWrapper.checkUpdateCalls(listOf(
            UiState.CurrentWeatherDataShow(
                data = null,
                noConnection = true
            )
        ))
    }
    @Test
    fun test_other() {
        val currentWeatherLoadResult = CurrentWeatherLoadResult.Error(
            noConnection = false
        )
        val fakeCurrentWeatherLiveDataWrapper = FakeCurrentWeatherLiveDataWrapper.Base()
        val currentWeatherLiveDataWrapperUpdate : CurrentWeatherLiveDataWrapper.Update = fakeCurrentWeatherLiveDataWrapper
        currentWeatherLoadResult.show(
            updateCurrentWeatherLiveData = currentWeatherLiveDataWrapperUpdate
        )
        fakeCurrentWeatherLiveDataWrapper.checkUpdateCalls(listOf(
            UiState.CurrentWeatherDataShow(
                data = null,
                noConnection = false
            )
        ))
    }
}