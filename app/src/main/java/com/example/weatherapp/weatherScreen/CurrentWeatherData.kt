package com.example.weatherapp.weatherScreen

data class CurrentWeatherData(
    val icon : String,
    val degrees : Double,
    val windSpeed : Double,
    val windDeg : Int,
    val precip : Pair<Double, String>,
    val date : Long
)
