package com.example.weatherapp.city.list

import com.example.weatherapp.city.core.CityData
import com.example.weatherapp.core.LiveDataWrapper

interface CityListLiveDataWrapper {
    interface Read : LiveDataWrapper.Read<List<CityData>>
    interface Update : LiveDataWrapper.Update<List<CityData>>
    interface Mutable : Read, Update
    class Base : LiveDataWrapper.Base<List<CityData>>(), Mutable
}