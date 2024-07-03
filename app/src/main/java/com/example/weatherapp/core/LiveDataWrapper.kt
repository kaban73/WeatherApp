package com.example.weatherapp.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {
    interface Read<T> {
        fun liveData() : LiveData<T>
    }
    interface Update<T> {
        fun update(value : T)
    }
    interface Mutable<T> : Read<T>, Update<T>
    abstract class Base<T>(
        protected val liveData: MutableLiveData<T> = SingleLiveEvent()
    ) : Mutable<T> {
        override fun liveData(): LiveData<T> =
            liveData

        override fun update(value: T) {
            liveData.value = value
        }
    }
}