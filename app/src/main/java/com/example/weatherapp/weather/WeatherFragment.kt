package com.example.weatherapp.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.weatherapp.core.AbstractFragment
import com.example.weatherapp.core.App
import com.example.weatherapp.core.ProvideViewModel
import com.example.weatherapp.databinding.WeatherLayoutBinding
import com.example.weatherapp.main.MainActivity
import com.example.weatherapp.weather.futureWeather.FutureWeatherAdapter
import com.example.weatherapp.weather.todayWeather.TodayWeatherAdapter
import java.text.SimpleDateFormat
import java.util.Date

class WeatherFragment : AbstractFragment<WeatherLayoutBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): WeatherLayoutBinding {
        return WeatherLayoutBinding.inflate(inflater, container, false)
    }

    private val todayWeatherAdapter = TodayWeatherAdapter()
    private val futureWeatherAdapter = FutureWeatherAdapter()
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b.todayWeatherRecyclerView.adapter = todayWeatherAdapter
        b.futureWeatherRecyclerView.adapter = futureWeatherAdapter
        weatherViewModel = (activity as ProvideViewModel).viewModel(WeatherViewModel::class.java)

        b.autoCompleteTextView.setOnClickListener {
            weatherViewModel.changeCity()
        }

        weatherViewModel.cityLiveData().observe(viewLifecycleOwner) {
            weatherViewModel.updateWeatherInfo(it)
        }
        weatherViewModel.currentWeatherLiveData().observe(viewLifecycleOwner) {
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
        weatherViewModel.todayWeatherLiveData().observe(viewLifecycleOwner) {
            todayWeatherAdapter.update(it)
        }
        weatherViewModel.futureWeatherLiveData().observe(viewLifecycleOwner) {
            futureWeatherAdapter.update(it)
        }
        weatherViewModel.init("Ulyanovsk")
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}