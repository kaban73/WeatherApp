package com.example.weatherapp.weatherScreen

data class FutureWeatherData(
    val icon : String,
    val date : Long,
    val minDegrees : Double,
    val maxDegrees : Double
)