package com.example.weatherapp.main

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals

interface FakeNavigation : Navigation.Mutable {
    fun checkUpdateCalled(expected : List<Screen>)
    class Base : FakeNavigation {
        private val callsList = mutableListOf<Screen>()
        override fun checkUpdateCalled(expected: List<Screen>) {
            assertEquals(expected, callsList)
        }

        override fun liveData(): LiveData<Screen> {
            throw IllegalStateException("don't used in this test")
        }

        override fun update(value: Screen) {
            callsList.add(value)
        }

    }
}