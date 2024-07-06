package com.example.weatherapp.repository.weather

import com.example.weatherapp.repository.weather.response.CurrentWeatherInfo
import com.example.weatherapp.repository.weather.response.CurrentWeatherMain
import com.example.weatherapp.repository.weather.response.CurrentWeatherResponse
import com.example.weatherapp.repository.weather.response.CurrentWeatherWind
import com.example.weatherapp.repository.weather.response.FutureWeatherList
import com.example.weatherapp.repository.weather.response.FutureWeatherMain
import com.example.weatherapp.repository.weather.response.FutureWeatherResponse
import com.example.weatherapp.repository.weather.response.FutureWeatherWeather
import com.example.weatherapp.repository.weather.response.TodayWeatherInfo
import com.example.weatherapp.repository.weather.response.TodayWeatherMain
import com.example.weatherapp.repository.weather.response.TodayWeatherResponse
import com.example.weatherapp.repository.weather.response.TodayWeatherWeatherInfo
import java.lang.Exception

interface FakeWeatherService : WeatherService{
    fun expectSuccess()

    fun expectException(exception: Exception)

    class Base : FakeWeatherService {
        private val fakeWeather = mutableMapOf(
            "current" to CurrentWeatherResponse(
                weather = listOf(CurrentWeatherInfo(
                    icon = "icon"
                )),
                main = CurrentWeatherMain(
                    temp = 0.0
                ),
                wind = CurrentWeatherWind(
                    speed = 0.0,
                    deg = 0
                ),
                rain = null,
                snow = null,
                date = 0L
            ),
            "today" to TodayWeatherResponse(
                list= listOf(
                    TodayWeatherInfo(
                        date = 0L,
                        main = TodayWeatherMain(
                            temp = 0.0
                        ),
                        weather = listOf(
                            TodayWeatherWeatherInfo(
                                icon = "icon"
                            )
                        )
                    )
                )
            ),
            "future" to FutureWeatherResponse(
                list = listOf(FutureWeatherList(
                    date = 0L,
                    main = FutureWeatherMain(
                        temp = 0.0
                    ),
                    weather = listOf(FutureWeatherWeather(
                        icon = "icon"
                    ))
                ))
            )
        )
        private var expectSuccessResult : Boolean = false
        private lateinit var exceptionToThrow : Exception

        override suspend fun currentWeatherFetch(
            latitude: Double,
            longitude: Double,
            appid: String,
            units: String
        ): CurrentWeatherResponse {
            if (expectSuccessResult)
                return fakeWeather["current"] as CurrentWeatherResponse
            else
                throw exceptionToThrow
        }

        override suspend fun todayWeatherFetch(
            latitude: Double,
            longitude: Double,
            appid: String,
            units: String,
            count: Int
        ): TodayWeatherResponse {
            if (expectSuccessResult)
                return fakeWeather["today"] as TodayWeatherResponse
            else
                throw exceptionToThrow
        }

        override suspend fun futureWeatherFetch(
            latitude: Double,
            longitude: Double,
            appid: String,
            units: String
        ): FutureWeatherResponse {
            if (expectSuccessResult)
                return fakeWeather["future"] as FutureWeatherResponse
            else
                throw exceptionToThrow
        }

        override fun expectSuccess() {
            expectSuccessResult = true
        }

        override fun expectException(exception: Exception) {
            exceptionToThrow = exception
        }
    }
}