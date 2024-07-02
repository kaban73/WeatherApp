package com.example.weatherapp.currentWeather

import com.google.gson.annotations.SerializedName

data class CurrentWeatherData(
    @SerializedName("weather") val weather : List<CurrentWeatherInfo>,
    @SerializedName("main") val main : CurrentWeatherMain,
    @SerializedName("wind") val wind :CurrentWeatherWind,
    @SerializedName("rain") val rain : CurrentRainInfo?,
    @SerializedName("snow") val snow : CurrentSnowInfo?,
    @SerializedName("dt") val date : Long
)

data class CurrentWeatherInfo(
    @SerializedName("main") val mainInfo : String,
    @SerializedName("description") val description: String
)

data class CurrentWeatherMain(
    @SerializedName("temp") val temp : Double
)

data class CurrentWeatherWind (
    @SerializedName("speed") val speed : Double,
    @SerializedName("deg") val deg : Int
)
data class CurrentRainInfo(
    @SerializedName("1h") val precip : Double
)

data class CurrentSnowInfo(
    @SerializedName("1h") val precip : Double
)
