package com.example.weatherapp.weatherScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.cityScreen.ChooseCity
import com.example.weatherapp.cityScreen.CitiesAdapter
import com.example.weatherapp.cityScreen.CityData
import com.example.weatherapp.cityScreen.CityFragment
import com.example.weatherapp.core.AbstractFragment
import com.example.weatherapp.core.ProvideViewModel
import com.example.weatherapp.core.UiState
import com.example.weatherapp.databinding.CityFragmentBinding
import com.example.weatherapp.databinding.WeatherFragmentBinding
import com.example.weatherapp.weatherScreen.future.FutureWeatherAdapter
import com.example.weatherapp.weatherScreen.today.TodayWeatherAdapter

class WeatherFragment : AbstractFragment<WeatherFragmentBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): WeatherFragmentBinding {
        return WeatherFragmentBinding.inflate(inflater, container, false)
    }
    private lateinit var viewModel: WeatherViewModel
    private val todayWeatherAdapter = TodayWeatherAdapter()
    private val futureWeatherAdapter = FutureWeatherAdapter()
    private val citiesAdapter = CitiesAdapter(object  : ChooseCity {
        override fun choose(cityData: CityData) {
            CityFragment().viewModel.comeback()
            viewModel.load(GeoData(cityData.lat, cityData.lon))
        }
    })
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b.todayWeatherRecyclerView.adapter = todayWeatherAdapter
        b.futureWeatherRecyclerView.adapter = futureWeatherAdapter
        viewModel = (activity as ProvideViewModel).viewModel(WeatherViewModel::class.java)
        viewModel.liveData().observe(viewLifecycleOwner) {
            if (it is UiState.CityGeoDataShow) {
                it.show(b.searchCityEditText)
            }
            if (it is UiState.CurrentWeatherDataShow) {
                it.show(b.nowWeatherImageView, b.nowDegreesTextView, b.windTextView, b.precipTextView)
            }
            if (it is UiState.TodayWeatherDataShow) {
                it.show(todayWeatherAdapter, b.todayRangeDegreesTextView)
            }
            if (it is UiState.FutureWeatherDataShow) {
                it.show(futureWeatherAdapter)
            }
            if (it is UiState.CitiesListDataShow) {
                
            }
        }

        b.searchCityEditText.setOnClickListener{
            viewModel.changeCity(b.searchCityEditText.text.toString())
        }

        viewModel.load(GeoData(LAT, LON))
    }
    companion object { // temporarily
        private const val LAT = 54.3107593
        private const val LON = 48.3642771
    }
}