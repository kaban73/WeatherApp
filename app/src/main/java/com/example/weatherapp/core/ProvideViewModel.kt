package com.example.weatherapp.core

import androidx.lifecycle.ViewModel
import com.example.weatherapp.main.MainViewModel
import com.example.weatherapp.main.Navigation
import com.example.weatherapp.repository.city.CityRepository
import com.example.weatherapp.repository.weather.WeatherRepository
import com.example.weatherapp.weatherScreen.WeatherViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(viewModelClass : Class<T>) : T
    class Base(
        core : Core,
        private val clearViewModel: ClearViewModel
    ) : ProvideViewModel {
        private val navigation = Navigation.Base()
        private val weatherRepository = WeatherRepository.Base(core.weatherService())
        private val cityRepository = CityRepository.Base(core.cityService())
        private val liveDataWrapper = LiveDataWrapper.Base()
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T = when(viewModelClass) {
            MainViewModel::class.java -> MainViewModel(navigation)
            WeatherViewModel::class.java -> WeatherViewModel(weatherRepository, cityRepository, liveDataWrapper, navigation)
            else -> throw IllegalStateException("unknown viewModelClass $viewModelClass")
        } as T
    }
}