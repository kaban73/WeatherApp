package com.example.weatherapp.currentWeather

import com.example.weatherapp.core.LiveDataWrapper

interface CurrentWeatherLiveDataWrapper {
    interface Read : LiveDataWrapper.Read<CurrentWeatherData>
    interface Update : LiveDataWrapper.Update<CurrentWeatherData>
    interface Mutable : LiveDataWrapper.Mutable<CurrentWeatherData>
    class Base : LiveDataWrapper.Base<CurrentWeatherData>(), Mutable
}