package com.example.weatherapp.weather

import android.location.Location
import androidx.lifecycle.ViewModel
import com.example.weatherapp.city.core.CityData
import com.example.weatherapp.city.core.CityLivaDataWrapper
import com.example.weatherapp.city.repository.CityRepository
import com.example.weatherapp.city.CityScreen
import com.example.weatherapp.main.Navigation
import com.example.weatherapp.weather.currentWeather.CurrentWeatherLiveDataWrapper
import com.example.weatherapp.weather.currentWeather.CurrentWeatherRepository
import com.example.weatherapp.weather.futureWeather.FutureWeatherCalc
import com.example.weatherapp.weather.futureWeather.FutureWeatherLiveDataWrapper
import com.example.weatherapp.weather.futureWeather.FutureWeatherRepository
import com.example.weatherapp.weather.todayWeather.TodayWeatherData
import com.example.weatherapp.weather.todayWeather.TodayWeatherLiveDataWrapper
import com.example.weatherapp.weather.todayWeather.TodayWeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val cityRepository: CityRepository,
    private val currentWeatherRepository: CurrentWeatherRepository,
    private val todayWeatherRepository: TodayWeatherRepository,
    private val futureWeatherRepository: FutureWeatherRepository,
    private val cityLivaDataWrapper: CityLivaDataWrapper.Mutable,
    private val currentWeatherLiveDataWrapper: CurrentWeatherLiveDataWrapper.Mutable,
    private val todayWeatherLiveDataWrapper: TodayWeatherLiveDataWrapper.Mutable,
    private val futureWeatherLiveDataWrapper: FutureWeatherLiveDataWrapper.Mutable,
    private val navigation: Navigation.Update
) : ViewModel(){
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun init(location: Location?) {
        viewModelScope.launch {
            val cities = if (location != null)
                    cityRepository.load(location.latitude, location.longitude, "", "")
                else
                    cityRepository.load("Moscow", "", "")
            cityLivaDataWrapper.update(cities.last().let { CityData(it.name + ", " + it.state, it.lat, it.lon) })
        }
    }
    fun updateWeatherInfo(cityData: CityData) {
        viewModelScope.launch {
            val currentWeather = currentWeatherRepository.load(cityData.lat, cityData.lon)
            val todayWeather = ArrayList<TodayWeatherData>()
            todayWeatherRepository.load(cityData.lat, cityData.lon).list.forEach {
                todayWeather.add(TodayWeatherData(it.weather.last().icon, it.time.toString(), it.main.temp.toString()))
            }
            val futureWeatherCalc = FutureWeatherCalc.Base(futureWeatherRepository.load(cityData.lat, cityData.lon))
            val futureWeather = futureWeatherCalc.list()
            currentWeatherLiveDataWrapper.update(currentWeather)
            todayWeatherLiveDataWrapper.update(todayWeather)
            futureWeatherLiveDataWrapper.update(futureWeather)
        }
    }
    fun changeCity() {
        navigation.update(CityScreen)
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