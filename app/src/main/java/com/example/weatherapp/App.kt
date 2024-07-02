package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.city.CityRepository
import com.example.weatherapp.city.CityService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    lateinit var viewModel: ViewModel
    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create<CityService>(CityService::class.java)
        viewModel = ViewModel(CityRepository.Base(service))
    }
}