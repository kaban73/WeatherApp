package com.example.weatherapp.city.repository

import retrofit2.http.GET
import retrofit2.http.Query

interface CityService {
    @GET("/geo/1.0/direct")
    suspend fun fetch(
        @Query("q") cityName : String,
        @Query("limit") limit : String,
        @Query("appid") apiKey : String
        ) : List<CityResponse>
}