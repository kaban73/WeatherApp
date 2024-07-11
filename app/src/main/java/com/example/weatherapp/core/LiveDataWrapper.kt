package com.example.weatherapp.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {
    interface Read {
        fun liveData() : LiveData<UiState>
    }
    interface Update {
        fun update(value : UiState)
    }
    interface Mutable : Read, Update
    class Base(
        private val liveData : MutableLiveData<UiState> = SingleLiveEvent()
    ) : Mutable {
        override fun liveData(): LiveData<UiState> =
            liveData

        override fun update(value: UiState) {
            liveData.value = value
        }
    }
}