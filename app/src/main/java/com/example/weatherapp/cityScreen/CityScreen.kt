package com.example.weatherapp.cityScreen

import androidx.fragment.app.FragmentManager
import com.example.weatherapp.main.Screen

data class CityScreen(
    private val cityName : String
) : Screen {
    override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
        supportFragmentManager.beginTransaction()
            .add(containerId, CityFragment.newInstance(cityName))
            .hide(supportFragmentManager.fragments.last())
            .addToBackStack(CityFragment::class.java.name)
            .commit()
    }

}