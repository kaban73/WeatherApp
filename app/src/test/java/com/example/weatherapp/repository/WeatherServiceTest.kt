package com.example.weatherapp.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Please check https://openweathermap.org/api for Current Weather Data and 5 Day / 3 Hour Forecast
 * Functions in service differ by @GET(*some link*) annotation of Retrofit
 */

class WeatherServiceTest {
    @Test
    fun test() = runBlocking {
        val weatherService : FakeWeatherService = FakeWeatherService.Base()
        val actualCurrentWeather = weatherService.currentWeatherFetch(
            latitude = 0.00,
            longitude = 0.00
        )
        val expectedCurrentWeather = FakeCurrentWeatherResponse(
            "icon",
            0.0,
            0.0,
            0,
            null,
            0.0,
            0L
        )
        assertEquals(expectedCurrentWeather, actualCurrentWeather)

        val actualTodayWeather = weatherService.todayWeatherFetch(
            latitude = 0.0,
            longitude = 0.0
        )
        val expectedTodayWeather = FakeTodayWeatherResponse(
            0,
            0.0,
            "icon"
        )
        assertEquals(expectedTodayWeather, actualTodayWeather)

        val actualFutureWeather = weatherService.futureWeatherFetch(
            latitude = 0.0,
            longitude = 0.0
        )
        val expectedFutureWeather = FakeFutureWeatherResponse(
            0,
            0.0,
            "icon"
        )
        assertEquals(expectedFutureWeather, actualFutureWeather)
    }

    interface FakeWeatherService {
        companion object {
            private const val APPID = "API_KEY"
            private const val UNITS = "metrics"
            private const val COUNT = 5
        }
        // @GET("/data/2.5/weather")
        suspend fun currentWeatherFetch(
            latitude : Double,
            longitude : Double,
            appid : String = APPID,
            units : String = UNITS
        ) : FakeCurrentWeatherResponse

        // @GET("/data/2.5/forecast")
        suspend fun todayWeatherFetch(
            latitude : Double,
            longitude : Double,
            appid : String = APPID,
            units : String = UNITS,
            count : Int = COUNT
        ) : FakeTodayWeatherResponse

        // @GET("/data/2.5/forecast")
        suspend fun futureWeatherFetch(
            latitude : Double,
            longitude : Double,
            appid : String = APPID,
            units : String = UNITS
        ) : FakeFutureWeatherResponse
        class Base : FakeWeatherService {
            private val fakeWeather = mutableMapOf(
                "current" to FakeCurrentWeatherResponse(
                    "icon",
                    0.0,
                    0.0,
                    0,
                    null,
                    0.0,
                    0L
                ),
                "today" to FakeTodayWeatherResponse(
                    0,
                    0.0,
                    "icon"
                ),
                "future" to FakeFutureWeatherResponse(
                    0,
                    0.0,
                    "icon"
                )
            )

            override suspend fun currentWeatherFetch(
                latitude: Double,
                longitude: Double,
                appid: String,
                units: String
            ): FakeCurrentWeatherResponse {
                return fakeWeather["current"] as FakeCurrentWeatherResponse
            }

            override suspend fun todayWeatherFetch(
                latitude: Double,
                longitude: Double,
                appid: String,
                units: String,
                count: Int
            ): FakeTodayWeatherResponse {
                return fakeWeather["today"] as FakeTodayWeatherResponse
            }

            override suspend fun futureWeatherFetch(
                latitude: Double,
                longitude: Double,
                appid: String,
                units: String
            ): FakeFutureWeatherResponse {
                return fakeWeather["future"] as FakeFutureWeatherResponse
            }
        }
    }
    data class FakeCurrentWeatherResponse(
        val icon: String,
        val degrees: Double,
        val windSpeed : Double,
        val windDeg : Int,
        val precipRain : Double?,
        val precipSnow : Double?,
        val date: Long
    )
    data class FakeTodayWeatherResponse(
        val date : Long,
        val degrees: Double,
        val icon : String
    )
    data class FakeFutureWeatherResponse(
        val date : Long,
        val degrees : Double,
        val icon : String
    )
}