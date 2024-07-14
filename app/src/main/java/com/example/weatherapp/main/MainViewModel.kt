package com.example.weatherapp.main

import androidx.lifecycle.ViewModel
import com.example.weatherapp.core.LiveDataWrapper
import com.example.weatherapp.repository.city.CityRepository
import com.example.weatherapp.repository.weather.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val weatherRepository: WeatherRepository,
    private val cityRepository: CityRepository,
    private val liveDataWrapper: LiveDataWrapper.Mutable,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain : CoroutineDispatcher = Dispatchers.Main
) : ViewModel(){
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun liveData() =
        liveDataWrapper.liveData()
    fun load() {// load(geoData : GeoData(lat, lon))
        viewModelScope.launch(dispatcher) {
            val lat = 0.0
            val lon = 0.0
            cityRepository.load(lat, lon).show(liveDataWrapper)
            weatherRepository.currentWeatherLoad(lat, lon).show(liveDataWrapper)
            weatherRepository.todayWeatherLoad(lat, lon).show(liveDataWrapper)
            weatherRepository.futureWeatherLoad(lat, lon).show(liveDataWrapper)
        }
    }
}