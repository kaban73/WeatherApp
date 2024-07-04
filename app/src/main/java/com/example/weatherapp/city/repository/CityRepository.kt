package com.example.weatherapp.city.repository

interface CityRepository {
    suspend fun load(
        cityName : String,
        limit : String,
        apiKey : String
    ) : List<CityResponse>
    suspend fun load(
        lat : Double,
        lon : Double,
        limit : String,
        apiKey : String
    ) : List<CityResponse>
    class Base(
        private val service: CityService,
    ) : CityRepository {
        override suspend fun load(
            cityName: String,
            limit: String,
            apiKey: String
        ): List<CityResponse> {
            return service.fetch(cityName, "5", "4240ded606dd2468bd5ed39d7a005a32")
        }

        override suspend fun load(
            lat: Double,
            lon: Double,
            limit: String,
            apiKey: String
        ): List<CityResponse> {
            return service.fetch(lat, lon, "1", "4240ded606dd2468bd5ed39d7a005a32")
        }
    }
}