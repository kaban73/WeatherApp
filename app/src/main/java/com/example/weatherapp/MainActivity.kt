package com.example.weatherapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.core.App
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.futureWeather.FutureWeatherAdapter
import com.example.weatherapp.todayWeather.TodayWeatherAdapter
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private val todayWeatherAdapter = TodayWeatherAdapter()
    private val futureWeatherAdapter = FutureWeatherAdapter()
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.todayWeatherRecyclerView.adapter = todayWeatherAdapter
        b.futureWeatherRecyclerView.adapter = futureWeatherAdapter
        viewModel = (application as App).viewModel
        viewModel.cityLiveData().observe(this) {
            viewModel.updateWeatherInfo(it)
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
        viewModel.todayWeatherLiveData().observe(this) {
            todayWeatherAdapter.update(it)
        }
        viewModel.futureWeatherLiveData().observe(this) {
            futureWeatherAdapter.update(it)
        }
        viewModel.init("Ulyanovsk")

    }
}