package com.example.weatherapp.weatherScreen

import com.example.weatherapp.core.LiveDataWrapper

interface CurrentWeatherLiveDataWrapper {
    interface Read : LiveDataWrapper.Read<UiState.CurrentWeatherDataShow>
    interface Update : LiveDataWrapper.Update<UiState.CurrentWeatherDataShow>
    interface Mutable : Read, Update
    class Base : LiveDataWrapper.Base<UiState.CurrentWeatherDataShow>(), Mutable
}