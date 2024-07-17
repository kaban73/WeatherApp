package com.example.weatherapp.repository.weather

import com.example.weatherapp.core.LoadResult
import com.example.weatherapp.weatherScreen.current.CurrentWeatherData
import com.example.weatherapp.weatherScreen.future.FutureWeatherData
import com.example.weatherapp.weatherScreen.today.TodayWeatherData
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
        val currentWeatherExpected = LoadResult.CurrentWeatherSuccess(
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

        val todayWeatherActual = weatherRepository.todayWeatherLoad(
            latitude = 54.3107593,
            longitude = 48.3642771
        )
        val todayWeatherExpected = LoadResult.TodayWeatherSuccess(
            data = listOf(
                TodayWeatherData(
                    icon = "icon",
                    date = 0L,
                    degrees = 0.0
                )
            )
        )
        assertEquals(todayWeatherExpected, todayWeatherActual)

        /**
         * To solve this test, you need to find out UnixTime and JSON, which gives the Weather API 5 Day / 3 Hour Forecast
         * And in the future, write a converter class that will calculate the weather for days, depending on the UnixTime
         */
        val futureWeatherActual = weatherRepository.futureWeatherLoad(
            latitude = 54.3107593,
            longitude = 48.3642771
        )
        val futureWeatherExpected = LoadResult.FutureWeatherSuccess(
            data = listOf(
                FutureWeatherData(
                    icon = "icon",
                    date = "Friday, 2 January",
                    minDegrees = 1.0,
                    maxDegrees = 2.0
                )
            )
        )
        assertEquals(futureWeatherExpected, futureWeatherActual)
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
        val currentWeatherExpected = LoadResult.CurrentWeatherError(true)
        assertEquals(currentWeatherExpected, currentWeatherActual)

        val todayWeatherActual = weatherRepository.todayWeatherLoad(
            latitude = 54.3107593,
            longitude = 48.3642771
        )
        val todayWeatherExpected = LoadResult.TodayWeatherError(true)
        assertEquals(todayWeatherExpected, todayWeatherActual)

        val futureWeatherActual = weatherRepository.futureWeatherLoad(
            latitude = 54.3107593,
            longitude = 48.3642771
        )
        val futureWeatherExpected = LoadResult.FutureWeatherError(true)
        assertEquals(futureWeatherExpected, futureWeatherActual)
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
        val currentWeatherExpected = LoadResult.CurrentWeatherError(false)
        assertEquals(currentWeatherExpected, currentWeatherActual)

        val todayWeatherActual = weatherRepository.todayWeatherLoad(
            latitude = 54.3107593,
            longitude = 48.3642771
        )
        val todayWeatherExpected = LoadResult.TodayWeatherError(false)
        assertEquals(todayWeatherExpected, todayWeatherActual)

        val futureWeatherActual = weatherRepository.futureWeatherLoad(
            latitude = 54.3107593,
            longitude = 48.3642771
        )
        val futureWeatherExpected = LoadResult.FutureWeatherError(false)
        assertEquals(futureWeatherExpected, futureWeatherActual)
    }
}
