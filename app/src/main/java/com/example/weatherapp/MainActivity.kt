package com.example.weatherapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherapp.databinding.ActivityMainBinding
import java.util.Calendar
import java.util.Date
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private val hourWeatherDataList = mutableListOf<HourWeatherData>()
    private val hourAdapter = WeatherHourAdapter()
    private val dayAdapter = WeatherDayAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        createHours()
        val weatherDataArray = arrayOf(
            DayWeatherData("Monday", Date(), 20, 30),
            DayWeatherData("Tuesday", Date(), 18, 28),
            DayWeatherData("Wednesday", Date(), 22, 32),
        )

        b.todayWeatherRecyclerView.adapter = hourAdapter
        b.futureWeatherRecyclerView.adapter = dayAdapter

        hourAdapter.update(hourWeatherDataList)
        dayAdapter.update(weatherDataArray.toList())
    }

    fun createHours() {
        val hours = 24
        val randomHour = Calendar.getInstance()

        for (i in 1..hours) {
            randomHour.set(Calendar.HOUR_OF_DAY, i)
            hourWeatherDataList.add(
                HourWeatherData(randomHour.time, Random.nextInt(-20, 40))
            )
        }
    }
}