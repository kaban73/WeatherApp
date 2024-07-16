package com.example.weatherapp.cityScreen

import androidx.lifecycle.ViewModel
import com.example.weatherapp.core.ClearViewModel
import com.example.weatherapp.core.LiveDataWrapper
import com.example.weatherapp.main.Navigation
import com.example.weatherapp.main.Screen
import com.example.weatherapp.repository.city.CityRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CityViewModel(
    private val liveDataWrapper: LiveDataWrapper.Update,
    private val cityRepository: CityRepository,
    private val navigation: Navigation.Update,
    private val clearViewModel: ClearViewModel
) : ViewModel() {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun findCities(cityName : String) {
        viewModelScope.launch {
            cityRepository.load(cityName).show(liveDataWrapper)
        }
    }
    fun comeback() {
        navigation.update(Screen.Pop)
        clearViewModel.clear(CityViewModel::class.java)
    }
}