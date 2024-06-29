package com.example.weatherapp

import android.provider.ContactsContract.RawContacts.Data

data class DayWeatherData(
    val day : String,
    val data : Data,
    val minDegrees : Int,
    val maxDegrees : Int
)
