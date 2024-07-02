package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.weatherapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
//    private val hourAdapter = WeatherHourAdapter()
//    private val dayAdapter = WeatherDayAdapter()
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
//        b.todayWeatherRecyclerView.adapter = hourAdapter
//        b.futureWeatherRecyclerView.adapter = dayAdapter
        viewModel = (application as App).viewModel
        viewModel.cityLiveData().observe(this) {
            viewModel.updateCurrentWeather(it)
        }
        viewModel.currentWeatherLiveData().observe(this) {
            val unixTime = it.date
            val date = Date(unixTime * 1000)
            val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
            b.todayDateTextView.text = sdf.format(date)

            b.todayWeatherDegrees.text = it.main.temp.toString() + "C"

            b.rangeDegreesTodayWeatherTextView.text = "Min: ${it.main.temp}C Max: ${it.main.temp}C"

            b.windTodayWeatherTextView.text = "Wind: ${it.wind.speed} m/s ${it.wind.deg}"

            b.precipTodayWeatherTextView.text = if (it.rain != null)
                "Precip: ${it.rain.precip} mm rain"
            else if (it.snow != null)
                "Precip: ${it.snow.precip} mm snow"
            else
                "Precip: 0 mm"

        }
        viewModel.init("Ulyanovsk")

    }
}