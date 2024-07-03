package com.example.weatherapp.main

import com.example.weatherapp.core.LiveDataWrapper

interface Navigation {
    interface Read : LiveDataWrapper.Read<Screen>
    interface Update : LiveDataWrapper.Update<Screen>
    interface Mutable : Read, Update
    class Base : LiveDataWrapper.Base<Screen>(), Mutable
}