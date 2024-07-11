package com.example.weatherapp.repository.city

import java.lang.Exception

interface FakeCityService : CityService{
    fun expectSuccess()
    fun expectException(exception: Exception)
    class Base : FakeCityService {
        private val fakeCities = mutableMapOf(
            "A" to listOf(
                CityResponse(
                    name = "a",
                    lat = 1.1,
                    lon = 1.1,
                    country = "aa",
                    state = "aaa"
                )
            ),
            "B" to listOf(
                CityResponse(
                    name = "b",
                    lat = 1.2,
                    lon = 1.2,
                    country = "bb",
                    state = "bbb"
                )
            ),
            Pair(1.1, 1.1) to listOf(
                CityResponse(
                    name = "a",
                    lat = 1.1,
                    lon = 1.1,
                    country = "aa",
                    state = "aaa"
                )
            ),
            Pair(1.2, 1.2) to listOf(
                CityResponse(
                    name = "b",
                    lat = 1.2,
                    lon = 1.2,
                    country = "bb",
                    state = "bbb"
                )
            )
        )
        private var expectSuccessResult : Boolean = false
        private lateinit var exceptionToThrow : Exception
        override fun expectSuccess() {
            expectSuccessResult = true
        }

        override fun expectException(exception: Exception) {
            exceptionToThrow = exception
        }

        override suspend fun fetch(
            cityName: String,
            limit: Int,
            apiKey: String
        ): List<CityResponse> {
            if (expectSuccessResult)
                return fakeCities[cityName]!!
            else
                throw exceptionToThrow
        }

        override suspend fun fetch(
            lat: Double,
            lon: Double,
            limit: Int,
            apiKey: String
        ): List<CityResponse> {
            if (expectSuccessResult)
                return fakeCities[Pair(lat, lon)]!!
            else
                throw exceptionToThrow
        }

    }
}