package com.example.weatherapp.repository.weather

//import com.example.weatherapp.weather.FakeWeatherLiveDataWrapper
//import com.example.weatherapp.weatherScreen.CurrentWeatherData
//import com.example.weatherapp.weatherScreen.FutureWeatherData
//import com.example.weatherapp.core.UiState
//import com.example.weatherapp.weatherScreen.TodayWeatherData
//import com.example.weatherapp.core.LiveDataWrapper
//import org.junit.Test
//
//class LoadResultTest {
//    @Test
//    fun test_success() {
//        val currentLoadResult = LoadResult.CurrentWeatherSuccess(
//            data = CurrentWeatherData(
//                icon = "icon",
//                degrees = 0.0,
//                windSpeed = 0.0,
//                windDeg = 0,
//                precip = Pair(0.0, ""),
//                date = 0L
//            )
//        )
//        val weatherLiveDataWrapper = FakeWeatherLiveDataWrapper.Base()
//        val liveDataWrapperUpdate : LiveDataWrapper.Update = weatherLiveDataWrapper
//        currentLoadResult.show(
//            updateWeatherLiveData = liveDataWrapperUpdate
//        )
//        weatherLiveDataWrapper.checkUpdateCalls(listOf(
//            UiState.CurrentWeatherDataShow(
//                data = CurrentWeatherData(
//                    icon = "icon",
//                    degrees = 0.0,
//                    windSpeed = 0.0,
//                    windDeg = 0,
//                    precip = Pair(0.0, ""),
//                    date = 0L
//                ),
//                noConnection = null
//            )
//        ))
//
//        val todayLoadResult = LoadResult.TodayWeatherSuccess(
//            data = listOf(
//                TodayWeatherData(
//                    icon = "icon",
//                    date = 0L,
//                    degrees = 0.0,
//                )
//            )
//        )
//        todayLoadResult.show(
//            updateWeatherLiveData = liveDataWrapperUpdate
//        )
//        weatherLiveDataWrapper.checkUpdateCalls(
//            listOf(
//                UiState.CurrentWeatherDataShow(
//                    data = CurrentWeatherData(
//                        icon = "icon",
//                        degrees = 0.0,
//                        windSpeed = 0.0,
//                        windDeg = 0,
//                        precip = Pair(0.0, ""),
//                        date = 0L
//                    ),
//                    noConnection = null
//                ),
//                UiState.TodayWeatherDataShow(
//                    listOf(
//                        TodayWeatherData(
//                            icon = "icon",
//                            date = 0L,
//                            degrees = 0.0,
//                        )
//                    ), null
//                )
//            )
//        )
//        val futureWeatherResult = LoadResult.FutureWeatherSuccess(
//            data = listOf(
//                FutureWeatherData(
//                    icon = "icon",
//                    date = 0L,
//                    minDegrees = 0.0,
//                    maxDegrees = 0.0
//                )
//            )
//        )
//        futureWeatherResult.show(
//            updateWeatherLiveData = liveDataWrapperUpdate
//        )
//        weatherLiveDataWrapper.checkUpdateCalls(
//            listOf(
//                UiState.CurrentWeatherDataShow(
//                    data = CurrentWeatherData(
//                        icon = "icon",
//                        degrees = 0.0,
//                        windSpeed = 0.0,
//                        windDeg = 0,
//                        precip = Pair(0.0, ""),
//                        date = 0L
//                    ),
//                    noConnection = null
//                ),
//                UiState.TodayWeatherDataShow(
//                    listOf(
//                        TodayWeatherData(
//                            icon = "icon",
//                            date = 0L,
//                            degrees = 0.0,
//                        )
//                    ),
//                    noConnection = null
//                ),
//                UiState.FutureWeatherDataShow(
//                    data = listOf(
//                        FutureWeatherData(
//                            icon = "icon",
//                            date = 0L,
//                            minDegrees = 0.0,
//                            maxDegrees = 0.0
//                        )
//                    ),
//                    noConnection = null
//                )
//            )
//        )
//    }
//    @Test
//    fun test_no_connection() {
//        val currentLoadResult = LoadResult.CurrentWeatherError(
//            noConnection = true
//        )
//        val weatherLiveDataWrapper = FakeWeatherLiveDataWrapper.Base()
//        val liveDataWrapperUpdate : LiveDataWrapper.Update = weatherLiveDataWrapper
//        currentLoadResult.show(
//            updateWeatherLiveData = liveDataWrapperUpdate
//        )
//        weatherLiveDataWrapper.checkUpdateCalls(listOf(
//            UiState.CurrentWeatherDataShow(
//                data = null,
//                noConnection = true
//            )
//        ))
//        val todayLoadResult = LoadResult.TodayWeatherError(
//            noConnection = true
//        )
//        todayLoadResult.show(
//            updateWeatherLiveData = liveDataWrapperUpdate
//        )
//        weatherLiveDataWrapper.checkUpdateCalls(
//            listOf(
//                UiState.CurrentWeatherDataShow(
//                    data = null,
//                    noConnection = true
//                ),
//                UiState.TodayWeatherDataShow(
//                    data = null,
//                    noConnection = true
//                )
//            )
//        )
//        val futureLoadResult = LoadResult.FutureWeatherError(
//            noConnection = true
//        )
//        futureLoadResult.show(
//            updateWeatherLiveData = liveDataWrapperUpdate
//        )
//        weatherLiveDataWrapper.checkUpdateCalls(
//            listOf(
//                UiState.CurrentWeatherDataShow(
//                    data = null,
//                    noConnection = true
//                ),
//                UiState.TodayWeatherDataShow(
//                    data = null,
//                    noConnection = true
//                ),
//                UiState.FutureWeatherDataShow(
//                    data = null,
//                    noConnection = true
//                )
//            )
//        )
//    }
//    @Test
//    fun test_other() {
//        val currentLoadResult = LoadResult.CurrentWeatherError(
//            noConnection = false
//        )
//        val weatherLiveDataWrapper = FakeWeatherLiveDataWrapper.Base()
//        val liveDataWrapperUpdate : LiveDataWrapper.Update = weatherLiveDataWrapper
//        currentLoadResult.show(
//            updateWeatherLiveData = liveDataWrapperUpdate
//        )
//        weatherLiveDataWrapper.checkUpdateCalls(listOf(
//            UiState.CurrentWeatherDataShow(
//                data = null,
//                noConnection = false
//            )
//        ))
//        val todayLoadResult = LoadResult.TodayWeatherError(
//            noConnection = false
//        )
//        todayLoadResult.show(
//            updateWeatherLiveData = liveDataWrapperUpdate
//        )
//        weatherLiveDataWrapper.checkUpdateCalls(
//            listOf(
//                UiState.CurrentWeatherDataShow(
//                    data = null,
//                    noConnection = false
//                ),
//                UiState.TodayWeatherDataShow(
//                    data = null,
//                    noConnection = false
//                )
//            )
//        )
//        val futureLoadResult = LoadResult.FutureWeatherError(
//            noConnection = false
//        )
//        futureLoadResult.show(
//            updateWeatherLiveData = liveDataWrapperUpdate
//        )
//        weatherLiveDataWrapper.checkUpdateCalls(
//            listOf(
//                UiState.CurrentWeatherDataShow(
//                    data = null,
//                    noConnection = false
//                ),
//                UiState.TodayWeatherDataShow(
//                    data = null,
//                    noConnection = false
//                ),
//                UiState.FutureWeatherDataShow(
//                    data = null,
//                    noConnection = false
//                )
//            )
//        )
//    }
//}