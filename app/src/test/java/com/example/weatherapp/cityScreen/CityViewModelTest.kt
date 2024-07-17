package com.example.weatherapp.cityScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.cityScreen.list.CityListLiveDataWrapper
import com.example.weatherapp.core.ClearViewModel
import com.example.weatherapp.core.FakeLiveDataWrapper
import com.example.weatherapp.core.LoadResult
import com.example.weatherapp.core.UiState
import com.example.weatherapp.main.FakeNavigation
import com.example.weatherapp.main.Screen
import com.example.weatherapp.repository.FakeCityRepository
import com.example.weatherapp.repository.FakeWeatherRepository
import com.example.weatherapp.repository.city.CityResponse
import com.example.weatherapp.weatherScreen.GeoData
import com.example.weatherapp.weatherScreen.current.CurrentWeatherData
import com.example.weatherapp.weatherScreen.future.FutureWeatherData
import com.example.weatherapp.weatherScreen.today.TodayWeatherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CityViewModelTest {
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
    private lateinit var cityListLiveDataWrapper : FakeCityListLiveDataWrapper
    private lateinit var viewModel : CityViewModel
    private lateinit var navigation : FakeNavigation
    private lateinit var clearViewModel: FakeClearViewModel
    private fun initialize() {
        weatherRepository = FakeWeatherRepository.Base()
        cityRepository = FakeCityRepository.Base()
        liveDataWrapper = FakeLiveDataWrapper.Base()
        cityListLiveDataWrapper = FakeCityListLiveDataWrapper.Base()
        navigation = FakeNavigation.Base()
        clearViewModel = FakeClearViewModel.Base()
        viewModel = CityViewModel(
            liveDataWrapper = liveDataWrapper,
            cityListLiveDataWrapper = cityListLiveDataWrapper,
            weatherRepository = weatherRepository,
            cityRepository = cityRepository,
            navigation = navigation,
            clearViewModel = clearViewModel
        )
    }
    @Test
    fun test_find_cities() {
        val cityName = "City"
        cityRepository.expectResult(
            LoadResult.CityNameSuccess(
                data = listOf(
                    CityResponse(
                        cityName,
                        0.0,
                        0.0,
                        "city",
                        "city"
                    )
                )
            )
        )
        viewModel.findCities(cityName)
        cityListLiveDataWrapper.checkCalledValue(
            UiState.CitiesListDataShow(
                data = listOf(
                    CityResponse(
                        cityName,
                        0.0,
                        0.0,
                        "city",
                        "city"
                    )
                ),
                noConnection = null
            )
        )

        val noInternet = "noInternet"
        cityRepository.expectResult(
            LoadResult.CityNameError(
                noConnection = true
            )
        )
        viewModel.findCities(noInternet)
        cityListLiveDataWrapper.checkCalledValue(
            UiState.CitiesListDataShow(
                data = null,
                noConnection = true
            )
        )

        val nonExistenCityName = "nonExistenCityName"
        cityRepository.expectResult(
            LoadResult.CityNameError(
                noConnection = false
            )
        )
        viewModel.findCities(nonExistenCityName)
        cityListLiveDataWrapper.checkCalledValue(
            UiState.CitiesListDataShow(
                data = null,
                noConnection = false
            )
        )
    }
    @Test
    fun test_changeCity() {
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
                            "",
                            0.0,
                            0.0
                        )
                    )
                )
            )
        )
        viewModel.changeCity(
            GeoData(
            latitude = 0.0,
            longitude = 0.0
        )
        )
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
                            "",
                            0.0,
                            0.0
                        )
                    ),
                    noConnection = null
                )
            )
        )
    }
    @Test
    fun test_no_internet_connection() {
        cityRepository.expectResult(
            LoadResult.CityGeoError(
                noConnection = true
            )
        )
        weatherRepository.expectResult(
            mapOf(
                "current" to LoadResult.CurrentWeatherError(
                    noConnection = true
                ),
                "today" to LoadResult.TodayWeatherError(
                    noConnection = true
                ),
                "future" to LoadResult.FutureWeatherError(
                    noConnection = true
                )
            )
        )
        viewModel.changeCity(GeoData(
            latitude = 0.0,
            longitude = 0.0
        ))
        liveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.CityGeoDataShow(
                    data = null,
                    noConnection = true
                ),
                UiState.CurrentWeatherDataShow(
                    data = null,
                    noConnection = true
                ),
                UiState.TodayWeatherDataShow(
                    data = null,
                    noConnection = true
                ),
                UiState.FutureWeatherDataShow(
                    data = null,
                    noConnection = true
                )
            )
        )
    }
    @Test
    fun test_other_exception() {
        cityRepository.expectResult(
            LoadResult.CityGeoError(
                noConnection = false
            )
        )
        weatherRepository.expectResult(
            mapOf(
                "current" to LoadResult.CurrentWeatherError(
                    noConnection = false
                ),
                "today" to LoadResult.TodayWeatherError(
                    noConnection = false
                ),
                "future" to LoadResult.FutureWeatherError(
                    noConnection = false
                )
            )
        )
        viewModel.changeCity(GeoData(
            latitude = 0.0,
            longitude = 0.0
        ))
        liveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.CityGeoDataShow(
                    data = null,
                    noConnection = false
                ),
                UiState.CurrentWeatherDataShow(
                    data = null,
                    noConnection = false
                ),
                UiState.TodayWeatherDataShow(
                    data = null,
                    noConnection = false
                ),
                UiState.FutureWeatherDataShow(
                    data = null,
                    noConnection = false
                )
            )
        )
    }
    @Test
    fun test_comeback() {
        viewModel.comeback()
        navigation.checkUpdateCalled(listOf(Screen.Pop))
        clearViewModel.checkClearCalled(CityViewModel::class.java)
    }
    private interface FakeCityListLiveDataWrapper : CityListLiveDataWrapper.Mutable {
        fun checkCalledValue(expected : UiState.CitiesListDataShow)
        class Base : FakeCityListLiveDataWrapper {
            private lateinit var actual : UiState
            override fun checkCalledValue(expected : UiState.CitiesListDataShow) {
                assertEquals(expected, actual)
            }

            override fun liveData(): LiveData<UiState.CitiesListDataShow> {
                throw IllegalStateException("dont used in this test")
            }

            override fun update(value: UiState) {
                actual = value
            }

        }
    }
    private interface FakeClearViewModel : ClearViewModel {

        fun checkClearCalled(expected: Class<out ViewModel>)

        class Base : FakeClearViewModel {
            private lateinit var actual: Class<out ViewModel>

            override fun checkClearCalled(expected: Class<out ViewModel>) {
                assertEquals(expected, actual)
            }

            override fun <T : ViewModel> clear(viewModelClass: Class<T>) {
                actual = viewModelClass
            }
        }
    }
}