package com.example.weatherapp.repository.weather

import com.example.weatherapp.weatherScreen.CurrentWeatherData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class WeatherRepositoryTest {
    @Test
    fun test_success() = runBlocking {
        val service = FakeWeatherService.Base()
        service.expectSuccess()
        val weatherRepository = WeatherRepository.Base(
            service = service
        )
        val currentWeatherActual = weatherRepository.currentWeatherLoad(
            latitude = 54.3107593,
            longitude = 48.3642771
        )
        val currentWeatherExpected = CurrentWeatherLoadResult.Success(
            data = CurrentWeatherData(
                icon = "icon",
                degrees = 0.0,
                windSpeed = 0.0,
                windDeg = 0,
                precip = Pair(0.0, ""),
                date = 0L
            )
        )
        assertEquals(currentWeatherExpected, currentWeatherActual)
    }
    @Test
    fun test_no_connection_exception() = runBlocking {
        val service = FakeWeatherService.Base()
        service.expectException(UnknownHostException())
        val weatherRepository = WeatherRepository.Base(
            service = service
        )
        val currentWeatherActual = weatherRepository.currentWeatherLoad(
            latitude = 54.3107593,
            longitude = 48.3642771
        )
        val currentWeatherExpected = CurrentWeatherLoadResult.Error(true)
        assertEquals(currentWeatherExpected, currentWeatherActual)
    }

    @Test
    fun test_other_exception() = runBlocking {
        val service = FakeWeatherService.Base()
        service.expectException(Exception())
        val weatherRepository = WeatherRepository.Base(
            service = service
        )
        val currentWeatherActual = weatherRepository.currentWeatherLoad(
            latitude = 54.3107593,
            longitude = 48.3642771
        )
        val currentWeatherExpected = CurrentWeatherLoadResult.Error(false)
        assertEquals(currentWeatherExpected, currentWeatherActual)
    }
}
