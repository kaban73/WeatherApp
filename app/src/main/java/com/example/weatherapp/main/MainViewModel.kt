package com.example.weatherapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.weather.WeatherScreen

class MainViewModel(
    private val navigation : Navigation.Mutable
)  : Navigation.Read, ViewModel(){
    override fun liveData(): LiveData<Screen> =
        navigation.liveData()
    fun init(firstRun : Boolean) {
        if (firstRun)
            navigation.update(WeatherScreen)
    }
}