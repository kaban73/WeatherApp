package com.example.weatherapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.weatherapp.core.App
import com.example.weatherapp.core.ProvideViewModel
import com.example.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProvideViewModel {
    private lateinit var viewModel: MainViewModel
    private lateinit var b : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        viewModel = viewModel(MainViewModel::class.java)
        viewModel.liveData().observe(this){
            it.show(supportFragmentManager, b.container.id)
        }
        viewModel.init(savedInstanceState==null)
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return (application as App).viewModel(viewModelClass)
    }
}