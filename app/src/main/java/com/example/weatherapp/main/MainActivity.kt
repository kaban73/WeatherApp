package com.example.weatherapp.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.weatherapp.core.App
import com.example.weatherapp.core.ProvideViewModel
import com.example.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProvideViewModel {
    private lateinit var b : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        mainViewModel = viewModel(MainViewModel::class.java)

        mainViewModel.liveData().observe(this) {
            it.show(supportFragmentManager, b.container.id)
        }

        mainViewModel.init(savedInstanceState == null)
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return (application as App).viewModel(viewModelClass)
    }
}