package com.example.weatherapp.todayWeather

import com.example.weatherapp.core.LiveDataWrapper

interface TodayWeatherLiveDataWrapper {
    interface Mutable : LiveDataWrapper.Mutable<List<TodayWeatherData>>
    class Base : LiveDataWrapper.Base<List<TodayWeatherData>>(), Mutable
}