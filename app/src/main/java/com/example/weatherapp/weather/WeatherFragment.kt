package com.example.weatherapp.weather

import android.Manifest
import android.app.Activity
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.location.component1
import androidx.core.location.component2
import com.example.weatherapp.R
import com.example.weatherapp.core.AbstractFragment
import com.example.weatherapp.core.AppPermissions
import com.example.weatherapp.core.LocationHelper
import com.example.weatherapp.core.ProvideViewModel
import com.example.weatherapp.databinding.CityLayoutBinding
import com.example.weatherapp.databinding.WeatherLayoutBinding
import com.example.weatherapp.weather.futureWeather.FutureWeatherAdapter
import com.example.weatherapp.weather.todayWeather.TodayWeatherAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Date

class WeatherFragment : AbstractFragment<WeatherLayoutBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): WeatherLayoutBinding {
        return WeatherLayoutBinding.inflate(inflater, container, false)
    }

    private lateinit var locationCallback: LocationCallback

    private val todayWeatherAdapter = TodayWeatherAdapter()
    private val futureWeatherAdapter = FutureWeatherAdapter()
    private lateinit var weatherViewModel: WeatherViewModel
    var location : Location? = null

    private lateinit var fusedLocationClient : FusedLocationProviderClient

    private fun isLocationEnabled(): Boolean {
        val locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        try {
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return false
    }

    private fun createLocationRequest(){
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            10000
        ).setMinUpdateIntervalMillis(5000).build()
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val client = LocationServices.getSettingsClient(requireActivity().applicationContext)
        val task = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener {  }
        task.addOnFailureListener {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
                permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION,
                    false) || permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION,
                    false) -> {
                    Toast.makeText(requireActivity().applicationContext, "ok", Toast.LENGTH_SHORT).show()
                    if(isLocationEnabled()) {
                        if (ActivityCompat.checkSelfPermission(
                                requireActivity().applicationContext,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                requireActivity().applicationContext,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            val result =  fusedLocationClient.getCurrentLocation(
                                Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                                CancellationTokenSource().token
                            )
                            result.addOnCompleteListener{
                                location = it.result
                            }
                            return@registerForActivityResult
                        } else {
                            val result =  fusedLocationClient.getCurrentLocation(
                                Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                                CancellationTokenSource().token
                            )
                            result.addOnCompleteListener{
                                location = it.result
                                weatherViewModel.init(location)
                            }
                        }

                    } else {
                        Toast.makeText(requireActivity().applicationContext, "turn loc", Toast.LENGTH_SHORT).show()
                        createLocationRequest()
                    }
                } else -> {
                Toast.makeText(requireActivity().applicationContext, "no access", Toast.LENGTH_SHORT).show()
            }
            }
        }
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

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
            b.autoCompleteTextView.setText(it.name)
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
        weatherViewModel.init(location)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}