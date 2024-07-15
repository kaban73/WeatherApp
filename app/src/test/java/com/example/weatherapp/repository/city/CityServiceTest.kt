package com.example.weatherapp.repository.city

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityServiceTest {
    @Test
    fun test() = runBlocking {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val cityService = retrofit.create(CityService::class.java)
        val actualCities : List<CityResponse> = cityService.fetch(
            cityName = "Ulyanovsk",
            apiKey = API_KEY,
            limit = LIMIT
        )
        val expectedCities = listOf(
            CityResponse(
                name = "Ulyanovsk",
                lat = 54.3107593,
                lon = 48.3642771,
                country = "RU",
                state = "Ulyanovsk Oblast"
            )
        )
        assertEquals(expectedCities, actualCities)

        val actualCitiesReverse : List<CityResponse> = cityService.fetch(
            lat = 54.3107593,
            lon = 48.3642771,
            apiKey = API_KEY,
            limit = LIMIT
        )
        val expectedCitiesReverse = listOf(
            CityResponse(
                name = "Ulyanovsk",
                lat = 54.3107593,
                lon = 48.3642771,
                country = "RU",
                state = "Ulyanovsk Oblast"
            )
        )
        assertEquals(expectedCitiesReverse, actualCitiesReverse)
    }
    companion object {
        private const val API_KEY = "4240ded606dd2468bd5ed39d7a005a32" // Write Your API key
        private const val LIMIT = 5
    }
}