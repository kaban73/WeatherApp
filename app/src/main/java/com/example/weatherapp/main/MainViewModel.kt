package com.example.weatherapp.main

import androidx.lifecycle.ViewModel
import com.example.weatherapp.weatherScreen.WeatherScreen

class MainViewModel(
    private val navigation: Navigation.Mutable
) : ViewModel(){
    fun liveData() = navigation.liveData()
    fun init(firstRun : Boolean) {
        if (firstRun)
            navigation.update(WeatherScreen)
    }
}