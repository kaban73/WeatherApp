package com.example.weatherapp

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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