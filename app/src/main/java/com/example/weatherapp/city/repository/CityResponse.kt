package com.example.weatherapp.city.repository

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("name") val name: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("state") val state : String
)
