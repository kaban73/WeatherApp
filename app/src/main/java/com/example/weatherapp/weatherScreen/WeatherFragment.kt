package com.example.weatherapp.weatherScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherapp.core.AbstractFragment
import com.example.weatherapp.databinding.WeatherFragmentBinding

class WeatherFragment : AbstractFragment<WeatherFragmentBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): WeatherFragmentBinding {
        return WeatherFragmentBinding.inflate(inflater, container, false)
    }
}