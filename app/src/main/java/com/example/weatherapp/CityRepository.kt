package com.example.weatherapp

interface CityRepository {
    suspend fun load(
        cityName : String,
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
    }
}