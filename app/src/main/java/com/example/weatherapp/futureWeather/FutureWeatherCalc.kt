package com.example.weatherapp.futureWeather

import java.text.SimpleDateFormat
import java.util.Date


interface FutureWeatherCalc {
    fun list() : List<FutureWeatherData>
    class Base(
        private val futureWeatherResponse: FutureWeatherResponse
    ) : FutureWeatherCalc {
        override fun list(): List<FutureWeatherData> {
            val result = ArrayList<FutureWeatherData>()
            val list = futureWeatherResponse.list
            val currData = getData(list.first().dt)
            val filteredList = list.filter { getData(it.dt) > currData }
            var newData = getData(filteredList.first().dt)
            var minDegrees = Double.MAX_VALUE
            var maxDegrees = Double.MIN_VALUE
            for(data in filteredList) {
                val crData = getData(data.dt)
                if (crData != newData) {
                    result.add(FutureWeatherData("", newData, minDegrees.toString(), maxDegrees.toString()))
                    minDegrees = Double.MAX_VALUE
                    maxDegrees = Double.MIN_VALUE
                    newData = crData
                }
                if (data.main.temp < minDegrees)
                    minDegrees = data.main.temp
                else if (data.main.temp > maxDegrees)
                    maxDegrees = data.main.temp
            }
            return result
        }

        private fun getData(unixTime : Long) : String {
            val date = Date(unixTime * 1000)
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            return sdf.format(date)
        }
    }
}