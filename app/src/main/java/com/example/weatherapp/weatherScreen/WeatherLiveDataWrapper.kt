package com.example.weatherapp.weatherScreen

import com.example.weatherapp.core.LiveDataWrapper

interface WeatherLiveDataWrapper {
    interface Read : LiveDataWrapper.Read<UiState>
    interface Update :  LiveDataWrapper.Update<UiState>
    interface Mutable : Read, Update
    class Base : LiveDataWrapper.Base<UiState>(), Mutable
}