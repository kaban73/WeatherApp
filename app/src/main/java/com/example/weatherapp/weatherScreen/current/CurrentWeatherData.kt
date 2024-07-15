package com.example.weatherapp.weatherScreen.current

data class CurrentWeatherData(
    val icon : String,
    val degrees : Double,
    val windSpeed : Double,
    val windDeg : Int,
    val precip : Pair<Double, String>,
    val date : Long
)
