package com.example.weatherapp.cityScreen

import android.widget.TextView

data class CityData(
    val name : String,
    val lat : Double,
    val lon : Double
) {
    fun areItemsTheSame(cityData: CityData) = cityData.name == name && cityData.lon == lon && cityData.lat == lat
    fun areContentsTheSame(cityData: CityData) = cityData.name == name
    fun show(textView: TextView) = textView.setText(name)
    fun choose(chooseCity : ChooseCity) = chooseCity.choose(this)
}
