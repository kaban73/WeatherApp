package com.example.weatherapp.repository.city

import retrofit2.http.GET
import retrofit2.http.Query

interface CityService {
    @GET("/geo/1.0/direct")
    suspend fun fetch(
        @Query("q") cityName : String,
        @Query("limit") limit : Int = LIMIT,
        @Query("appid") apiKey : String = API_KEY
    ) : List<CityResponse>
    @GET("/geo/1.0/reverse")
    suspend fun fetch(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("limit") limit : Int = LIMIT,
        @Query("appid") apiKey : String = API_KEY
    ) : List<CityResponse>
    companion object {
        private const val API_KEY = "4240ded606dd2468bd5ed39d7a005a32"
        private const val LIMIT = 5
    }
}