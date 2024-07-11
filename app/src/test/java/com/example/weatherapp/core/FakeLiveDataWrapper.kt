package com.example.weatherapp.core

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals

interface FakeLiveDataWrapper : LiveDataWrapper.Mutable {
    fun checkUpdateCalls(expected : List<UiState>)
    class Base : FakeLiveDataWrapper {
        private val actual = mutableListOf<UiState>()
        override fun checkUpdateCalls(expected: List<UiState>) {
            assertEquals(expected, actual)
        }

        override fun liveData(): LiveData<UiState> {
            throw IllegalStateException("don't used in this test")
        }

        override fun update(value: UiState) {
            actual.add(value)
        }
    }
}