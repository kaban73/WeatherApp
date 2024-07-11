package com.example.weatherapp.repository.city

//import com.example.weatherapp.city.FakeCityGeoLiveDataWrapper
//import com.example.weatherapp.city.FakeCityListLiveDataWrapper
//import com.example.weatherapp.core.FakeLiveDataWrapper
//import com.example.weatherapp.core.LiveDataWrapper
//import com.example.weatherapp.core.LoadResult
//import com.example.weatherapp.core.UiState
//import org.junit.Test
//
//class CityLoadResultTest {
//    @Test
//    fun test_success() {
//        val cityNameCityLoadResult = LoadResult.CityNameSuccess(
//            data = listOf(
//                CityResponse(
//                    name = "a",
//                    lat = 1.1,
//                    lon = 1.1,
//                    country = "aa",
//                    state = "aaa"
//                )
//            )
//        )
//        val citiesListLiveDataWrapper = FakeLiveDataWrapper.Base()
//        val citiesListLiveDataWrapperUpdate : LiveDataWrapper.Update = citiesListLiveDataWrapper
//        cityNameCityLoadResult.show(
//            updateLiveData = citiesListLiveDataWrapperUpdate
//        )
//        citiesListLiveDataWrapper.checkUpdateCalls(
//            listOf(
//                UiState.CitiesListDataShow(
//                    listOf(
//                        CityResponse(
//                            name = "a",
//                            lat = 1.1,
//                            lon = 1.1,
//                            country = "aa",
//                            state = "aaa"
//                        )
//                    ),
//                    noConnection = null
//                )
//            )
//        )
//        val cityGeoCityLoadResult = LoadResult.CityGeoSuccess(
//            CityResponse(
//                name = "b",
//                lat = 1.2,
//                lon = 1.2,
//                country = "bb",
//                state = "bbb"
//            )
//        )
//        val cityGeoLiveDataWrapperUpdate : LiveDataWrapper.Update = cityGeoLiveDataWrapper
//        cityGeoCityLoadResult.show(
//            updateCityGeoLiveData = cityGeoLiveDataWrapperUpdate
//        )
//        cityGeoLiveDataWrapper.checkUpdateCalls(
//            CityResponse(
//                name = "b",
//                lat = 1.2,
//                lon = 1.2,
//                country = "bb",
//                state = "bbb"
//            )
//        )
//    }
//}