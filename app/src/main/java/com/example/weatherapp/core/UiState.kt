package com.example.weatherapp.core

import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.weatherapp.R
import com.example.weatherapp.repository.city.CityResponse
import com.example.weatherapp.weatherScreen.current.CurrentWeatherData
import com.example.weatherapp.weatherScreen.future.FutureWeatherAdapter
import com.example.weatherapp.weatherScreen.future.FutureWeatherData
import com.example.weatherapp.weatherScreen.today.TodayWeatherAdapter
import com.example.weatherapp.weatherScreen.today.TodayWeatherData

interface UiState {
    data class CurrentWeatherDataShow(
        private val data : CurrentWeatherData?,
        private val noConnection: Boolean?
    ) : UiState {
        fun show(
            imageView: ImageView,
            nowDegreesTextView: TextView,
            windTextView: TextView,
            precipTextView: TextView
        ) {
            if (data != null) {
                val resId = getResourceIdForWeatherIcon(data.icon)
                if (resId != 0)
                    imageView.setImageResource(resId)
                else
                    imageView.setImageResource(R.drawable.ic_something_wrong)
                nowDegreesTextView.text = "${data.degrees}C"
                val windDir = getWindDirection(data.windDeg)
                windTextView.text = "Wind: ${"%.1f".format(data.windSpeed * 3.6)} km/h ${windDir}"
                precipTextView.text = "Precip: ${data.precip.first} mm ${data.precip.second}"
            }
        }
        private fun getWindDirection(degrees: Int): String {
            val directions = listOf("North", "North-East", "East", "South-East", "South", "South-West", "West", "North-West")
            val index = ((degrees + 22.5) / 45).toInt() and 7
            return directions[index]
        }
    }
    data class TodayWeatherDataShow(
        private val data: List<TodayWeatherData>?,
        private val noConnection: Boolean?
    ) : UiState {
        fun show(
            adapter : TodayWeatherAdapter,
            todayRangeDegreesTextView: TextView
        ) {
            if (data != null) {
                adapter.update(data)
                val rangeDegrees = findRangeDegrees(data)
                todayRangeDegreesTextView.text = "Min: ${rangeDegrees.first}C; Max: ${rangeDegrees.second}C"
            }
        }

        private fun findRangeDegrees(data: List<TodayWeatherData>): Pair<Double, Double> {
            var min = Double.MAX_VALUE
            var max = Double.MIN_VALUE
            data.forEach {
                if (it.degrees < min)
                    min = it.degrees
                if (it.degrees > max)
                    max = it.degrees
            }
            return Pair(min, max)
        }
    }
    data class FutureWeatherDataShow(
        private val data : List<FutureWeatherData>?,
        private val noConnection: Boolean?
    ) : UiState {
        fun show(adapter : FutureWeatherAdapter) {
            if (data != null)
                adapter.update(data)
        }
    }
    data class CitiesListDataShow(
        private val data: List<CityResponse>?,
        private val noConnection: Boolean?
    ) : UiState
    data class CityGeoDataShow(
        private val data: CityResponse?,
        private val noConnection: Boolean?
    ) : UiState {
        fun show(editText: EditText) {
            if (data != null) {
                editText.setText("${data.name}, ${data.state}, ${data.country}")
            } else {

            }
        }
    }
    fun getResourceIdForWeatherIcon(iconName: String): Int {
        return try {
            val field = R.mipmap::class.java.getField("ic_${iconName}")
            field.getInt(null)
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }
}