package com.example.weatherapp.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {
    lateinit var factory: ViewModelFactory
    private val clear : ClearViewModel = object : ClearViewModel {
        override fun <T : ViewModel> clear(viewModelClass: Class<T>) {
            factory.clear(viewModelClass)
        }
    }

    override fun onCreate() {
        super.onCreate()
        val provideViewModel = ProvideViewModel.Base(
            Core(),
            clear
        )
        factory = ViewModelFactory.Base(
            provideViewModel
        )
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return factory.viewModel(viewModelClass)
    }
}