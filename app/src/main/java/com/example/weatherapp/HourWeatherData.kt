package com.example.weatherapp

import android.provider.ContactsContract.RawContacts.Data

data class HourWeatherData(
    val hour : Data,
    val degrees : Int
)
