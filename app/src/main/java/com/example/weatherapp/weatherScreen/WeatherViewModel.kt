package com.example.weatherapp.weatherScreen

import androidx.lifecycle.ViewModel
import com.example.weatherapp.cityScreen.CityScreen
import com.example.weatherapp.core.LiveDataWrapper
import com.example.weatherapp.main.Navigation
import com.example.weatherapp.repository.city.CityRepository
import com.example.weatherapp.repository.city.CityResponse
import com.example.weatherapp.repository.weather.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val cityRepository: CityRepository,
    private val liveDataWrapper: LiveDataWrapper.Mutable,
    private val navigation: Navigation.Update,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain : CoroutineDispatcher = Dispatchers.Main
) : ViewModel(){
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun liveData() =
        liveDataWrapper.liveData()
    fun load(geoData : GeoData) {
        viewModelScope.launch {
            cityRepository.load(geoData.latitude, geoData.longitude).show(liveDataWrapper)
            weatherRepository.currentWeatherLoad(geoData.latitude, geoData.longitude).show(liveDataWrapper)
            weatherRepository.todayWeatherLoad(geoData.latitude, geoData.longitude).show(liveDataWrapper)
            weatherRepository.futureWeatherLoad(geoData.latitude, geoData.longitude).show(liveDataWrapper)
        }
    }
    fun changeCity(cityName : String) {
        navigation.update(CityScreen(cityName))
    }
}