package com.example.weatherapp.repository.weather

import com.example.weatherapp.repository.weather.response.CurrentWeatherResponse
import com.example.weatherapp.repository.weather.response.FutureWeatherResponse
import com.example.weatherapp.repository.weather.response.TodayWeatherResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * Please check https://openweathermap.org/api for Current Weather Data and 5 Day / 3 Hour Forecast
 * Functions in service differ by @GET(*some link*) annotation of Retrofit
 * To write data classes, you need to check json-requests
 */

class WeatherServiceTest {
    @Test
    fun test() = runBlocking {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val weatherService : WeatherService = retrofit.create(WeatherService::class.java)
        val fakeWeatherService: FakeWeatherService = retrofit.create(FakeWeatherService::class.java)
        val actualCurrentWeather : CurrentWeatherResponse = fakeWeatherService.currentWeatherFetch(
            latitude = 54.3107593,
            longitude = 48.3642771,
            appid = API_KEY,
            units = UNITS
        )
        val expectedCurrentWeather = weatherService.currentWeatherFetch(
            latitude = 54.3107593,
            longitude = 48.3642771,
            appid = API_KEY,
            units = UNITS,
        )
        assertEquals(expectedCurrentWeather, actualCurrentWeather)

        val actualTodayWeather : TodayWeatherResponse = fakeWeatherService.todayWeatherFetch(
            latitude = 54.3107593,
            longitude = 48.3642771,
            appid = API_KEY,
            units = UNITS,
            count = COUNT
        )
        val expectedTodayWeather = weatherService.todayWeatherFetch(
            latitude = 54.3107593,
            longitude = 48.3642771,
            appid = API_KEY,
            units = UNITS,
            count = COUNT
        )
        assertEquals(expectedTodayWeather, actualTodayWeather)

        val actualFutureWeather : FutureWeatherResponse = fakeWeatherService.futureWeatherFetch(
            latitude = 54.3107593,
            longitude = 48.3642771,
            appid = API_KEY,
            units = UNITS
        )
        val expectedFutureWeather = weatherService.futureWeatherFetch(
            latitude = 54.3107593,
            longitude = 48.3642771,
            appid = API_KEY,
            units = UNITS
        )
        assertEquals(expectedFutureWeather, actualFutureWeather)
    }
    companion object {
        private const val API_KEY = "4240ded606dd2468bd5ed39d7a005a32"// Write Your API key
        private const val UNITS = "metric"
        private const val COUNT = 5
    }
}