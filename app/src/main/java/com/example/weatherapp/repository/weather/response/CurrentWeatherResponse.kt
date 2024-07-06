package com.example.weatherapp.repository.weather.response

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("weather") val weather : List<CurrentWeatherInfo>,
    @SerializedName("main") val main : CurrentWeatherMain,
    @SerializedName("wind") val wind : CurrentWeatherWind,
    @SerializedName("rain") val rain : CurrentRainInfo?,
    @SerializedName("snow") val snow : CurrentSnowInfo?,
    @SerializedName("dt") val date : Long
)

data class CurrentWeatherInfo(
    @SerializedName("icon") val icon : String,
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