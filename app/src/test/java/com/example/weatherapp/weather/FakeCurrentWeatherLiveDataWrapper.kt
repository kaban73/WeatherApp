package com.example.weatherapp.weather

import androidx.lifecycle.LiveData
import com.example.weatherapp.weatherScreen.CurrentWeatherLiveDataWrapper
import com.example.weatherapp.weatherScreen.UiState
import org.junit.Assert.assertEquals

interface FakeCurrentWeatherLiveDataWrapper : CurrentWeatherLiveDataWrapper.Mutable {
    fun checkUpdateCalls(expected : List<UiState>)
    class Base : FakeCurrentWeatherLiveDataWrapper {
        private val actualCallsList = mutableListOf<UiState.CurrentWeatherDataShow>()
        override fun checkUpdateCalls(expected: List<UiState>) {
            assertEquals(expected, actualCallsList)
        }

        override fun liveData(): LiveData<UiState.CurrentWeatherDataShow> {
            throw IllegalStateException("not used in test")
        }

        override fun update(value: UiState.CurrentWeatherDataShow) {
            actualCallsList.add(value)
        }

    }
}