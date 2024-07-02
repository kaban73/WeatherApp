package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.city.CityLivaDataWrapper
import com.example.weatherapp.city.CityRepository
import com.example.weatherapp.city.CityResponse
import com.example.weatherapp.currentWeather.CurrentWeatherLiveDataWrapper
import com.example.weatherapp.currentWeather.CurrentWeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ViewModel(
    private val cityRepository: CityRepository,
    private val currentWeatherRepository: CurrentWeatherRepository,
    private val cityLivaDataWrapper: CityLivaDataWrapper.Mutable,
    private val currentWeatherLiveDataWrapper: CurrentWeatherLiveDataWrapper.Mutable
) {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun init(cityName : String) {
        viewModelScope.launch {
            val cities = cityRepository.load(cityName, "", "")
            cityLivaDataWrapper.update(cities.last())
        }
    }
    fun updateCurrentWeather(cityResponse: CityResponse) {
        viewModelScope.launch {
            val currentWeather = currentWeatherRepository.load(cityResponse.lat, cityResponse.lon)
            currentWeatherLiveDataWrapper.update(currentWeather)
        }
    }
    fun cityLiveData() =
        cityLivaDataWrapper.liveData()
    fun currentWeatherLiveData() =
        currentWeatherLiveDataWrapper.liveData()
}