package com.example.weatherapp.weather

import androidx.lifecycle.LiveData
import com.example.weatherapp.weatherScreen.UiState
import com.example.weatherapp.weatherScreen.WeatherLiveDataWrapper
import org.junit.Assert.assertEquals

interface FakeWeatherLiveDataWrapper : WeatherLiveDataWrapper.Mutable{
    fun checkUpdateCalls(expected : List<UiState>)
    class Base : FakeWeatherLiveDataWrapper {
        private val actualList = mutableListOf<UiState>()
        override fun checkUpdateCalls(expected: List<UiState>) {
            assertEquals(expected, actualList)
        }

        override fun liveData(): LiveData<UiState> {
            throw IllegalStateException("not used in test")
        }

        override fun update(value: UiState) {
            actualList.add(value)
        }
    }
}