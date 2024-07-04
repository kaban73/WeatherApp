package com.example.weatherapp.city.core

import androidx.lifecycle.ViewModel
import com.example.weatherapp.city.list.CityListLiveDataWrapper
import com.example.weatherapp.city.repository.CityRepository
import com.example.weatherapp.core.ClearViewModel
import com.example.weatherapp.main.Navigation
import com.example.weatherapp.main.Screen
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityViewModel(
    private val cityListLiveDataWrapper: CityListLiveDataWrapper.Mutable,
    private val cityLiveDataWrapper: CityLivaDataWrapper.Mutable,
    private val cityRepository: CityRepository,
    private val navigation: Navigation.Update,
    private val clearViewModel: ClearViewModel,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun cityListLiveData() =
        cityListLiveDataWrapper.liveData()
    fun init(editText : TextInputEditText) {
        editText.setText(cityLiveDataWrapper.liveData().value?.name ?: "")
    }
    fun findCity(name : String) {
        viewModelScope.launch(dispatcher) {
            val list = ArrayList<CityData>()
            cityRepository.load(name, "", "").forEach {
                list.add(CityData(it.name + ", " + it.state, it.lat, it.lon))
            }
            withContext(dispatcherMain) {
                cityListLiveDataWrapper.update(list)
            }
        }
    }
    fun chooseCity(cityData: CityData) {
        viewModelScope.launch(dispatcher) {
            withContext(dispatcherMain) {
                cityLiveDataWrapper.update(cityData)
            }
        }
    }
    fun comeback() {
        clearViewModel.clear(CityViewModel::class.java)
        navigation.update(Screen.Pop)
    }
}