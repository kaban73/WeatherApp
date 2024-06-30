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
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private val hourWeatherDataList = mutableListOf<HourWeatherData>()
    private val hourAdapter = WeatherHourAdapter()
    private val dayAdapter = WeatherDayAdapter()
    private lateinit var viewModel: ViewModel
    private var options = ArrayList<String>()
    private lateinit var citiesAdapter : ArrayAdapter<String>
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

        viewModel = (application as App).viewModel

        viewModel.liveData.observe(this) {
            val newOptions = ArrayList<String>()
            it.forEach {
                newOptions.add(it.name)
            }
            options = newOptions
            citiesAdapter.clear()
            citiesAdapter.addAll(options)
        }

        viewModel.init("u")

        citiesAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, options)
        b.autoCompleteTextView.setAdapter(citiesAdapter)
        b.autoCompleteTextView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                viewModel.init(b.autoCompleteTextView.text.toString())
                b.autoCompleteTextView.post{b.autoCompleteTextView.showDropDown()}
                true
            } else {
                false
            }
        }
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