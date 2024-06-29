package com.example.weatherapp

import java.util.Date

data class DayWeatherData(
    val day : String,
    val data : Date,
    val minDegrees : Int,
    val maxDegrees : Int
)
