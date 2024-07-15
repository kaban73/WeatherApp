package com.example.weatherapp.weatherScreen

import com.example.weatherapp.core.FakeLiveDataWrapper
import com.example.weatherapp.core.LoadResult
import com.example.weatherapp.core.UiState
import com.example.weatherapp.repository.city.CityRepository
import com.example.weatherapp.repository.city.CityResponse
import com.example.weatherapp.repository.weather.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class WeatherViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        initialize()
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    private lateinit var weatherRepository: FakeWeatherRepository
    private lateinit var cityRepository: FakeCityRepository
    private lateinit var liveDataWrapper: FakeLiveDataWrapper
    private lateinit var viewModel : WeatherViewModel
    private fun initialize() {
        weatherRepository = FakeWeatherRepository.Base()
        cityRepository = FakeCityRepository.Base()
        liveDataWrapper = FakeLiveDataWrapper.Base()
        viewModel = WeatherViewModel(
            weatherRepository = weatherRepository,
            cityRepository = cityRepository,
            liveDataWrapper = liveDataWrapper
        )
    }
    @Test
    fun test() {
        cityRepository.expectResult(
            LoadResult.CityGeoSuccess(
                data = CityResponse(
                    "City",
                    0.0,
                    0.0,
                    "city",
                    "city"
                )
            )
        )
        weatherRepository.expectResult(
            mapOf(
                "current" to LoadResult.CurrentWeatherSuccess(
                    data = CurrentWeatherData(
                        "icon",
                        0.0,
                        0.0,
                        0,
                        Pair(0.0, ""),
                        0L
                    )
                ),
                "today" to LoadResult.TodayWeatherSuccess(
                    data = listOf(
                        TodayWeatherData(
                            "icon",
                            0L,
                            0.0
                        )
                    )
                ),
                "future" to LoadResult.FutureWeatherSuccess(
                    data = listOf(
                        FutureWeatherData(
                            "icon",
                            0L,
                            0.0,
                            0.0
                        )
                    )
                )
            )
        )
        viewModel.load(GeoData(
            latitude = 0.0,
            longitude = 0.0
        ))
        liveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.CityGeoDataShow(
                    data = CityResponse(
                        "City",
                        0.0,
                        0.0,
                        "city",
                        "city"
                    ),
                    noConnection = null
                ),
                UiState.CurrentWeatherDataShow(
                    data = CurrentWeatherData(
                        "icon",
                        0.0,
                        0.0,
                        0,
                        Pair(0.0, ""),
                        0L
                    ),
                    noConnection = null
                ),
                UiState.TodayWeatherDataShow(
                    data = listOf(
                        TodayWeatherData(
                            "icon",
                            0L,
                            0.0
                        )
                    ),
                    noConnection = null
                ),
                UiState.FutureWeatherDataShow(
                    data = listOf(
                        FutureWeatherData(
                            "icon",
                            0L,
                            0.0,
                            0.0
                        )
                    ),
                    noConnection = null
                )
            )
        )
    }
    private interface FakeWeatherRepository : WeatherRepository {
        fun expectResult(results : Map<String, LoadResult>)
        class Base : FakeWeatherRepository {
            private var results = mapOf<String, LoadResult>()
            override fun expectResult(results : Map<String, LoadResult>) {
                this.results = results
            }

            override suspend fun currentWeatherLoad(
                latitude: Double,
                longitude: Double
            ): LoadResult {
                return results["current"]!!
            }

            override suspend fun todayWeatherLoad(latitude: Double, longitude: Double): LoadResult {
                return results["today"]!!
            }

            override suspend fun futureWeatherLoad(
                latitude: Double,
                longitude: Double
            ): LoadResult {
                return results["future"]!!
            }
        }
    }
    private interface FakeCityRepository : CityRepository {
        fun expectResult(result : LoadResult)
        class Base : FakeCityRepository {
            private lateinit var result: LoadResult
            override fun expectResult(result: LoadResult) {
                this.result = result
            }

            override suspend fun load(cityName: String): LoadResult {
                return result
            }

            override suspend fun load(latitude: Double, longitude: Double): LoadResult {
                return result
            }

        }
    }
}