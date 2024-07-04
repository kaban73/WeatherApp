package com.example.weatherapp.core

import android.app.Application
import androidx.lifecycle.ViewModel

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