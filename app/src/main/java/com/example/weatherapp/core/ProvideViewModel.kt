package com.example.weatherapp.core

import androidx.lifecycle.ViewModel
import com.example.weatherapp.cityScreen.CityViewModel
import com.example.weatherapp.cityScreen.list.CityListLiveDataWrapper
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
        private val citiesListLiveDataWrapper = CityListLiveDataWrapper.Base()
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T = when(viewModelClass) {
            MainViewModel::class.java -> MainViewModel(navigation)
            WeatherViewModel::class.java -> WeatherViewModel(weatherRepository, cityRepository, liveDataWrapper, navigation)
            CityViewModel::class.java -> CityViewModel(liveDataWrapper ,citiesListLiveDataWrapper, weatherRepository ,cityRepository, navigation, clearViewModel)
            else -> throw IllegalStateException("unknown viewModelClass $viewModelClass")
        } as T
    }
}