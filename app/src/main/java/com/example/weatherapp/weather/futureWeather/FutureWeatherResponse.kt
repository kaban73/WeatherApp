package com.example.weatherapp.weather.futureWeather

import com.google.gson.annotations.SerializedName

data class FutureWeatherResponse(
    @SerializedName("list") val list : List<FutureWeatherList>
)
data class FutureWeatherList(
    @SerializedName("dt") val dt : Long,
    @SerializedName("main") val main : FutureWeatherMain,
    @SerializedName("weather") val weather : List<FutureWeatherWeather>
)
data class FutureWeatherMain(
    @SerializedName("temp") val temp : Double
)
data class FutureWeatherWeather(
    @SerializedName("icon") val icon : String
)
