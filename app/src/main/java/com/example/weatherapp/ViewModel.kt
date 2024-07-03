package com.example.weatherapp

import com.example.weatherapp.city.CityLivaDataWrapper
import com.example.weatherapp.city.CityRepository
import com.example.weatherapp.city.CityResponse
import com.example.weatherapp.currentWeather.CurrentWeatherLiveDataWrapper
import com.example.weatherapp.currentWeather.CurrentWeatherRepository
import com.example.weatherapp.futureWeather.FutureWeatherCalc
import com.example.weatherapp.futureWeather.FutureWeatherData
import com.example.weatherapp.futureWeather.FutureWeatherLiveDataWrapper
import com.example.weatherapp.futureWeather.FutureWeatherRepository
import com.example.weatherapp.todayWeather.TodayWeatherData
import com.example.weatherapp.todayWeather.TodayWeatherLiveDataWrapper
import com.example.weatherapp.todayWeather.TodayWeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ViewModel(
    private val cityRepository: CityRepository,
    private val currentWeatherRepository: CurrentWeatherRepository,
    private val todayWeatherRepository: TodayWeatherRepository,
    private val futureWeatherRepository: FutureWeatherRepository,
    private val cityLivaDataWrapper: CityLivaDataWrapper.Mutable,
    private val currentWeatherLiveDataWrapper: CurrentWeatherLiveDataWrapper.Mutable,
    private val todayWeatherLiveDataWrapper: TodayWeatherLiveDataWrapper.Mutable,
    private val futureWeatherLiveDataWrapper: FutureWeatherLiveDataWrapper.Mutable
) {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun init(cityName : String) {
        viewModelScope.launch {
            val cities = cityRepository.load(cityName, "", "")
            cityLivaDataWrapper.update(cities.last())
        }
    }
    fun updateWeatherInfo(cityResponse: CityResponse) {
        viewModelScope.launch {
            val currentWeather = currentWeatherRepository.load(cityResponse.lat, cityResponse.lon)
            val todayWeather = ArrayList<TodayWeatherData>()
            todayWeatherRepository.load(cityResponse.lat, cityResponse.lon).list.forEach {
                todayWeather.add(TodayWeatherData(it.weather.last().icon, it.time.toString(), it.main.temp.toString()))
            }
            val futureWeatherCalc = FutureWeatherCalc.Base(futureWeatherRepository.load(cityResponse.lat, cityResponse.lon))
            val futureWeather = futureWeatherCalc.list()
            currentWeatherLiveDataWrapper.update(currentWeather)
            todayWeatherLiveDataWrapper.update(todayWeather)
            futureWeatherLiveDataWrapper.update(futureWeather)
        }
    }
    fun cityLiveData() =
        cityLivaDataWrapper.liveData()
    fun currentWeatherLiveData() =
        currentWeatherLiveDataWrapper.liveData()
    fun todayWeatherLiveData() =
        todayWeatherLiveDataWrapper.liveData()
    fun futureWeatherLiveData() =
        futureWeatherLiveDataWrapper.liveData()
}