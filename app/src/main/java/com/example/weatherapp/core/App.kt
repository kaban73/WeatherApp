package com.example.weatherapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.weatherapp.weather.WeatherViewModel
import com.example.weatherapp.city.CityLivaDataWrapper
import com.example.weatherapp.city.CityRepository
import com.example.weatherapp.city.CityService
import com.example.weatherapp.city.CityViewModel
import com.example.weatherapp.main.MainViewModel
import com.example.weatherapp.main.Navigation
import com.example.weatherapp.weather.currentWeather.CurrentWeatherLiveDataWrapper
import com.example.weatherapp.weather.currentWeather.CurrentWeatherRepository
import com.example.weatherapp.weather.currentWeather.CurrentWeatherService
import com.example.weatherapp.weather.futureWeather.FutureWeatherLiveDataWrapper
import com.example.weatherapp.weather.futureWeather.FutureWeatherRepository
import com.example.weatherapp.weather.futureWeather.FutureWeatherService
import com.example.weatherapp.weather.todayWeather.TodayWeatherLiveDataWrapper
import com.example.weatherapp.weather.todayWeather.TodayWeatherRepository
import com.example.weatherapp.weather.todayWeather.TodayWeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application(), ProvideViewModel {
    lateinit var viewModelFactory: ViewModelFactory
    private val clearViewModel = object : ClearViewModel {
        override fun <T : ViewModel> clear(viewModelClass: Class<T>) {
            viewModelFactory.clear(viewModelClass)
        }
    }
    override fun onCreate() {
        super.onCreate()
        val provideViewModel = ProvideViewModel.Base(Core(), clearViewModel)
        viewModelFactory = ViewModelFactory.Base(provideViewModel)
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return viewModelFactory.viewModel(viewModelClass)
    }

}