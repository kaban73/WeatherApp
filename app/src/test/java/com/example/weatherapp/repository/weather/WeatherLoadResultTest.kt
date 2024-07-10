package com.example.weatherapp.repository.weather

import com.example.weatherapp.weather.FakeWeatherLiveDataWrapper
import com.example.weatherapp.weatherScreen.CurrentWeatherData
import com.example.weatherapp.weatherScreen.FutureWeatherData
import com.example.weatherapp.weatherScreen.UiState
import com.example.weatherapp.weatherScreen.TodayWeatherData
import com.example.weatherapp.weatherScreen.WeatherLiveDataWrapper
import org.junit.Test

class WeatherLoadResultTest {
    @Test
    fun test_success() {
        val currentWeatherLoadResult = WeatherLoadResult.CurrentWeatherSuccess(
            data = CurrentWeatherData(
                icon = "icon",
                degrees = 0.0,
                windSpeed = 0.0,
                windDeg = 0,
                precip = Pair(0.0, ""),
                date = 0L
            )
        )
        val weatherLiveDataWrapper = FakeWeatherLiveDataWrapper.Base()
        val weatherLiveDataWrapperUpdate : WeatherLiveDataWrapper.Update = weatherLiveDataWrapper
        currentWeatherLoadResult.show(
            updateWeatherLiveData = weatherLiveDataWrapperUpdate
        )
        weatherLiveDataWrapper.checkUpdateCalls(listOf(
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

        val todayWeatherLoadResult = WeatherLoadResult.TodayWeatherSuccess(
            data = listOf(
                TodayWeatherData(
                    icon = "icon",
                    date = 0L,
                    degrees = 0.0,
                )
            )
        )
        todayWeatherLoadResult.show(
            updateWeatherLiveData = weatherLiveDataWrapperUpdate
        )
        weatherLiveDataWrapper.checkUpdateCalls(
            listOf(
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
                ),
                UiState.TodayWeatherDataShow(
                    listOf(
                        TodayWeatherData(
                            icon = "icon",
                            date = 0L,
                            degrees = 0.0,
                        )
                    ), null
                )
            )
        )
        val futureWeatherResult = WeatherLoadResult.FutureWeatherSuccess(
            data = listOf(
                FutureWeatherData(
                    icon = "icon",
                    date = 0L,
                    minDegrees = 0.0,
                    maxDegrees = 0.0
                )
            )
        )
        futureWeatherResult.show(
            updateWeatherLiveData = weatherLiveDataWrapperUpdate
        )
        weatherLiveDataWrapper.checkUpdateCalls(
            listOf(
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
                ),
                UiState.TodayWeatherDataShow(
                    listOf(
                        TodayWeatherData(
                            icon = "icon",
                            date = 0L,
                            degrees = 0.0,
                        )
                    ),
                    noConnection = null
                ),
                UiState.FutureWeatherDataShow(
                    data = listOf(
                        FutureWeatherData(
                            icon = "icon",
                            date = 0L,
                            minDegrees = 0.0,
                            maxDegrees = 0.0
                        )
                    ),
                    noConnection = null
                )
            )
        )
    }
    @Test
    fun test_no_connection() {
        val currentWeatherLoadResult = WeatherLoadResult.CurrentWeatherError(
            noConnection = true
        )
        val weatherLiveDataWrapper = FakeWeatherLiveDataWrapper.Base()
        val weatherLiveDataWrapperUpdate : WeatherLiveDataWrapper.Update = weatherLiveDataWrapper
        currentWeatherLoadResult.show(
            updateWeatherLiveData = weatherLiveDataWrapperUpdate
        )
        weatherLiveDataWrapper.checkUpdateCalls(listOf(
            UiState.CurrentWeatherDataShow(
                data = null,
                noConnection = true
            )
        ))
        val todayWeatherLoadResult = WeatherLoadResult.TodayWeatherError(
            noConnection = true
        )
        todayWeatherLoadResult.show(
            updateWeatherLiveData = weatherLiveDataWrapperUpdate
        )
        weatherLiveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.CurrentWeatherDataShow(
                    data = null,
                    noConnection = true
                ),
                UiState.TodayWeatherDataShow(
                    data = null,
                    noConnection = true
                )
            )
        )
        val futureWeatherLoadResult = WeatherLoadResult.FutureWeatherError(
            noConnection = true
        )
        futureWeatherLoadResult.show(
            updateWeatherLiveData = weatherLiveDataWrapperUpdate
        )
        weatherLiveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.CurrentWeatherDataShow(
                    data = null,
                    noConnection = true
                ),
                UiState.TodayWeatherDataShow(
                    data = null,
                    noConnection = true
                ),
                UiState.FutureWeatherDataShow(
                    data = null,
                    noConnection = true
                )
            )
        )
    }
    @Test
    fun test_other() {
        val currentWeatherLoadResult = WeatherLoadResult.CurrentWeatherError(
            noConnection = false
        )
        val weatherLiveDataWrapper = FakeWeatherLiveDataWrapper.Base()
        val weatherLiveDataWrapperUpdate : WeatherLiveDataWrapper.Update = weatherLiveDataWrapper
        currentWeatherLoadResult.show(
            updateWeatherLiveData = weatherLiveDataWrapperUpdate
        )
        weatherLiveDataWrapper.checkUpdateCalls(listOf(
            UiState.CurrentWeatherDataShow(
                data = null,
                noConnection = false
            )
        ))
        val todayWeatherLoadResult = WeatherLoadResult.TodayWeatherError(
            noConnection = false
        )
        todayWeatherLoadResult.show(
            updateWeatherLiveData = weatherLiveDataWrapperUpdate
        )
        weatherLiveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.CurrentWeatherDataShow(
                    data = null,
                    noConnection = false
                ),
                UiState.TodayWeatherDataShow(
                    data = null,
                    noConnection = false
                )
            )
        )
        val futureWeatherLoadResult = WeatherLoadResult.FutureWeatherError(
            noConnection = false
        )
        futureWeatherLoadResult.show(
            updateWeatherLiveData = weatherLiveDataWrapperUpdate
        )
        weatherLiveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.CurrentWeatherDataShow(
                    data = null,
                    noConnection = false
                ),
                UiState.TodayWeatherDataShow(
                    data = null,
                    noConnection = false
                ),
                UiState.FutureWeatherDataShow(
                    data = null,
                    noConnection = false
                )
            )
        )
    }
}