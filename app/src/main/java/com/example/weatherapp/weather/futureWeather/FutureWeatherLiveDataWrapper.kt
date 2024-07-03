package com.example.weatherapp.weather.futureWeather

import com.example.weatherapp.core.LiveDataWrapper

interface FutureWeatherLiveDataWrapper {
    interface Read : LiveDataWrapper.Read<List<FutureWeatherData>>
    interface Update : LiveDataWrapper.Update<List<FutureWeatherData>>
    interface Mutable : Read, Update
    class Base : LiveDataWrapper.Base<List<FutureWeatherData>>(), Mutable
}