package com.example.weatherapp.weatherScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.weatherapp.core.AbstractFragment
import com.example.weatherapp.core.ProvideViewModel
import com.example.weatherapp.core.UiState
import com.example.weatherapp.databinding.WeatherFragmentBinding
import com.example.weatherapp.weatherScreen.future.FutureWeatherAdapter
import com.example.weatherapp.weatherScreen.today.TodayWeatherAdapter
import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.LocationManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.weatherapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

class WeatherFragment : AbstractFragment<WeatherFragmentBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): WeatherFragmentBinding {
        return WeatherFragmentBinding.inflate(inflater, container, false)
    }

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


    private lateinit var viewModel: WeatherViewModel
    private val todayWeatherAdapter = TodayWeatherAdapter()
    private val futureWeatherAdapter = FutureWeatherAdapter()
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
                it.show(b.nowWeatherImageView, b.todayDateTextView ,b.nowDegreesTextView, b.windTextView, b.precipTextView)
            }
            if (it is UiState.TodayWeatherDataShow) {
                it.show(todayWeatherAdapter, b.todayRangeDegreesTextView)
            }
            if (it is UiState.FutureWeatherDataShow) {
                it.show(futureWeatherAdapter)
            }
        }
        b.searchCityEditText.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus)
                viewModel.changeCity(b.searchCityEditText.text.toString())
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity().applicationContext)
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
                permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION,
                    false) || permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION,
                    false) -> {
                    if(isLocationEnabled()) {
                        if (ActivityCompat.checkSelfPermission(
                                requireActivity().applicationContext,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                requireActivity().applicationContext,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            return@registerForActivityResult
                        } else {
                            val result =  fusedLocationClient.getCurrentLocation(
                                Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                                CancellationTokenSource().token
                            )
                            result.addOnCompleteListener{
                                viewModel.load(GeoData(it.result.latitude, it.result.longitude))
                            }
                        }

                    } else {
                        Toast.makeText(requireActivity().applicationContext, TURN_LOCATION_MSG, Toast.LENGTH_SHORT).show()
                        b.nowWeatherImageView.setImageResource(R.drawable.ic_unknown_location)
                        b.nowDegreesTextView.text = LOCATION_DISABLED_MSG
                        createLocationRequest()
                    }
                } else -> {
                Toast.makeText(requireActivity().applicationContext, NO_ACCESS_LOCATION_MSG, Toast.LENGTH_SHORT).show()
                b.nowWeatherImageView.setImageResource(R.drawable.ic_unknown_location)
                b.nowDegreesTextView.text = NO_ACCESS_LOCATION_MSG
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
    companion object {
        private const val TURN_LOCATION_MSG = "Please enable geolocation and restart app"
        private const val NO_ACCESS_LOCATION_MSG = "No access to geolocation"
        private const val LOCATION_DISABLED_MSG = "Geolocation is disabled"
    }
}