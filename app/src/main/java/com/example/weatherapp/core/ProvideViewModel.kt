package com.example.weatherapp.core

import androidx.lifecycle.ViewModel
import com.example.weatherapp.city.CityLivaDataWrapper
import com.example.weatherapp.city.CityRepository
import com.example.weatherapp.city.CityViewModel
import com.example.weatherapp.main.MainViewModel
import com.example.weatherapp.main.Navigation
import com.example.weatherapp.weather.WeatherViewModel
import com.example.weatherapp.weather.currentWeather.CurrentWeatherLiveDataWrapper
import com.example.weatherapp.weather.currentWeather.CurrentWeatherRepository
import com.example.weatherapp.weather.futureWeather.FutureWeatherLiveDataWrapper
import com.example.weatherapp.weather.futureWeather.FutureWeatherRepository
import com.example.weatherapp.weather.todayWeather.TodayWeatherLiveDataWrapper
import com.example.weatherapp.weather.todayWeather.TodayWeatherRepository

interface ProvideViewModel {
    fun<T : ViewModel> viewModel(viewModelClass: Class<T>) : T
    class Base(
        core: Core,
        private  val clearViewModel: ClearViewModel
    ) : ProvideViewModel {
        private val navigation = Navigation.Base()
        private val cityRepository = CityRepository.Base(core.cityService())
        private val currentWeatherRepository = CurrentWeatherRepository.Base(core.currentWeatherService())
        private val todayWeatherRepository = TodayWeatherRepository.Base(core.todayWeatherService())
        private val futureWeatherRepository = FutureWeatherRepository.Base(core.futureWeatherService())
        private val cityLiveDataWrapper = CityLivaDataWrapper.Base()
        private val currentWeatherLiveDataWrapper = CurrentWeatherLiveDataWrapper.Base()
        private val todayWeatherLiveDataWrapper = TodayWeatherLiveDataWrapper.Base()
        private val futureWeatherLiveDataWrapper = FutureWeatherLiveDataWrapper.Base()

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T = when(viewModelClass) {
            MainViewModel::class.java -> MainViewModel(navigation)
            WeatherViewModel::class.java -> WeatherViewModel(cityRepository,currentWeatherRepository,todayWeatherRepository,futureWeatherRepository,cityLiveDataWrapper,currentWeatherLiveDataWrapper, todayWeatherLiveDataWrapper, futureWeatherLiveDataWrapper, navigation)
            CityViewModel::class.java -> CityViewModel(navigation, clearViewModel)
            else -> throw IllegalStateException("unknown viewModelClass $viewModelClass")
        } as T
    }
}