package com.example.weatherapp.city.core

import com.example.weatherapp.core.LiveDataWrapper

interface CityLivaDataWrapper {
    interface Read : LiveDataWrapper.Read<CityData>
    interface Update : LiveDataWrapper.Update<CityData>
    interface Mutable : Read, Update
    class Base : LiveDataWrapper.Base<CityData>(), Mutable
}