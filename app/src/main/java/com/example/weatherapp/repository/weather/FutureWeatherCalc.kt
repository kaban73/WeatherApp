package com.example.weatherapp.repository.weather

import com.example.weatherapp.repository.weather.response.FutureWeatherResponse
import com.example.weatherapp.weatherScreen.future.FutureWeatherData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


interface FutureWeatherCalc {
    fun list() : List<FutureWeatherData>
    class Base(
        private val futureWeatherResponse: FutureWeatherResponse
    ) : FutureWeatherCalc {
        override fun list(): List<FutureWeatherData> {
            val result = ArrayList<FutureWeatherData>()
            val list = futureWeatherResponse.list
            val currData = getDate(list.first().date)
            val filteredList = list.filter { getDate(it.date) > currData }
            var newData = getDate(filteredList.first().date)
            var minDegrees = Double.MAX_VALUE
            var maxDegrees = Double.MIN_VALUE
            for(data in filteredList) {
                val crData = getDate(data.date)
                if (crData != newData) {
                    result.add(
                        FutureWeatherData(
                            data.weather.last().icon,
                            newData,
                            minDegrees,
                            maxDegrees
                        )
                    )
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

        private fun getDate(unixTime : Long) : String {
            val date = Date(unixTime * 1000L)
            val sdf = SimpleDateFormat("EEEE, d MMMM", Locale.getDefault())
            val formattedDate = sdf.format(date)
            return formattedDate
        }
    }
}