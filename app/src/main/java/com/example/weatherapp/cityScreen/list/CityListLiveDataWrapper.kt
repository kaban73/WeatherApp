package com.example.weatherapp.cityScreen.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.core.LiveDataWrapper
import com.example.weatherapp.core.SingleLiveEvent
import com.example.weatherapp.core.UiState

interface CityListLiveDataWrapper {
    interface Read {
        fun liveData() : LiveData<UiState.CitiesListDataShow>
    }
    interface Update: LiveDataWrapper.Update
    interface Mutable : Read , Update
    class Base(
        private val liveData: MutableLiveData<UiState.CitiesListDataShow> = SingleLiveEvent()
    ) : Mutable {
        override fun liveData(): LiveData<UiState.CitiesListDataShow> {
            return liveData
        }

        override fun update(value: UiState) {
            liveData.value = value as UiState.CitiesListDataShow
        }
    }
}