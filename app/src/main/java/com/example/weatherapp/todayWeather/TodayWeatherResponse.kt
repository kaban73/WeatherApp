package com.example.weatherapp.todayWeather

import com.google.gson.annotations.SerializedName

data class TodayWeatherResponse(
    @SerializedName("list") val list : List<TodayWeatherInfo>
)

data class TodayWeatherInfo(
    @SerializedName("dt") val time : Long,
    @SerializedName("main") val main : TodayWeatherMain,
    @SerializedName("weather") val weather : List<TodayWeatherWeatherInfo>
)

data class TodayWeatherMain(
    @SerializedName("temp") val temp : Double
)

data class TodayWeatherWeatherInfo(
    @SerializedName("icon") val icon : String
)