package com.example.weatherapp.city

import androidx.lifecycle.ViewModel
import com.example.weatherapp.core.ClearViewModel
import com.example.weatherapp.main.Navigation
import com.example.weatherapp.main.Screen

class CityViewModel(
    private val navigation: Navigation.Update,
    private val clearViewModel: ClearViewModel
): ViewModel() {


    fun comeback() {
        clearViewModel.clear(CityViewModel::class.java)
        navigation.update(Screen.Pop)
    }
}