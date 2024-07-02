package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.city.CityRepository
import com.example.weatherapp.city.CityResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ViewModel(
    private val repository: CityRepository
) {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val innerLiveData = MutableLiveData<List<CityResponse>>()
    val liveData
        get() = innerLiveData
    fun init(cityName : String) {
        viewModelScope.launch {
            val cities = repository.load(cityName, "", "")
            innerLiveData.value = cities
        }
    }

}