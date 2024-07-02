package com.example.weatherapp.city

import com.example.weatherapp.core.LiveDataWrapper

interface CityLivaDataWrapper {
    interface Read : LiveDataWrapper.Read<CityResponse>
    interface Update : LiveDataWrapper.Update<CityResponse>
    interface Mutable : LiveDataWrapper.Mutable<CityResponse>
    class Base : LiveDataWrapper.Base<CityResponse>(), Mutable
}