package com.example.weatherapp.repository.weather

import com.example.weatherapp.repository.weather.response.CurrentWeatherResponse
import com.example.weatherapp.repository.weather.response.FutureWeatherResponse
import com.example.weatherapp.repository.weather.response.TodayWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/weather")
    suspend fun currentWeatherFetch(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
        @Query("appid") appid : String = API_KEY,
        @Query("units") units : String = UNITS
    ) : CurrentWeatherResponse

    @GET("/data/2.5/forecast")
    suspend fun todayWeatherFetch(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
        @Query("appid") appid : String = API_KEY,
        @Query("units") units : String = UNITS,
        @Query("cnt") count : Int = COUNT
    ) : TodayWeatherResponse

    @GET("/data/2.5/forecast")
    suspend fun futureWeatherFetch(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
        @Query("appid") appid : String = API_KEY,
        @Query("units") units : String = UNITS,
    ) : FutureWeatherResponse

    companion object {
        private const val API_KEY = "84a747af1c1b16948f619fabcbd8a684"
        private const val UNITS = "metric"
        private const val COUNT = 5
    }
}