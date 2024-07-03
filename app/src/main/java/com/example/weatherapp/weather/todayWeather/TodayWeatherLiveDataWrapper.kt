package com.example.weatherapp.weather.todayWeather

import com.example.weatherapp.core.LiveDataWrapper

interface TodayWeatherLiveDataWrapper {
    interface Mutable : LiveDataWrapper.Mutable<List<TodayWeatherData>>
    class Base : LiveDataWrapper.Base<List<TodayWeatherData>>(), Mutable
}