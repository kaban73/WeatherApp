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
            val currData = getFiltrDate(list.first().date)
            val filteredList = list.filter { getFiltrDate(it.date) > currData }
            var newData = getDate(filteredList.first().date)
            var minDegrees = Double.MAX_VALUE
            var maxDegrees = Double.MIN_VALUE
            val icon = ArrayList<String>()
            for(data in filteredList) {
                val crData = getDate(data.date)
                icon.add(data.weather.last().icon)
                if (crData != newData) {
                    result.add(
                        FutureWeatherData(
                            icon[icon.size/2],
                            newData,
                            minDegrees,
                            maxDegrees
                        )
                    )
                    minDegrees = Double.MAX_VALUE
                    maxDegrees = Double.MIN_VALUE
                    newData = crData
                    icon.clear()
                }
                if (data.main.temp < minDegrees)
                    minDegrees = data.main.temp
                else if (data.main.temp > maxDegrees)
                    maxDegrees = data.main.temp
            }
            return result
        }

        private fun getFiltrDate(unixTime : Long) : String {
            val date = Date(unixTime * 1000)
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            return sdf.format(date)
        }

        private fun getDate(unixTime : Long) : String {
            val date = Date(unixTime * 1000L)
            val sdf = SimpleDateFormat("EEEE, d MMMM", Locale.getDefault())
            val formattedDate = sdf.format(date)
            return formattedDate
        }
    }
}