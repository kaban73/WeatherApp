package com.example.weatherapp.repository.city

import com.example.weatherapp.core.FakeLiveDataWrapper
import com.example.weatherapp.core.LiveDataWrapper
import com.example.weatherapp.core.LoadResult
import com.example.weatherapp.core.UiState
import org.junit.Test

class CityLoadResultTest {
    @Test
    fun test_success() {
        val cityNameCityLoadResult = LoadResult.CityNameSuccess(
            data = listOf(
                CityResponse(
                    name = "a",
                    lat = 1.1,
                    lon = 1.1,
                    country = "aa",
                    state = "aaa"
                )
            )
        )
        val cityLiveDataWrapper = FakeLiveDataWrapper.Base()
        val liveDataWrapperUpdate : LiveDataWrapper.Update = cityLiveDataWrapper
        cityNameCityLoadResult.show(
            updateLiveData = liveDataWrapperUpdate
        )
        cityLiveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.CitiesListDataShow(
                    data = listOf(
                        CityResponse(
                            name = "a",
                            lat = 1.1,
                            lon = 1.1,
                            country = "aa",
                            state = "aaa"
                        )
                    ),
                    noConnection = null
                )
            )
        )
        val cityGeoCityLoadResult = LoadResult.CityGeoSuccess(
            data = CityResponse(
                name = "b",
                lat = 1.2,
                lon = 1.2,
                country = "bb",
                state = "bbb"
            )
        )
        cityGeoCityLoadResult.show(
            updateLiveData = liveDataWrapperUpdate
        )
        cityLiveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.CitiesListDataShow(
                    data = listOf(
                        CityResponse(
                            name = "a",
                            lat = 1.1,
                            lon = 1.1,
                            country = "aa",
                            state = "aaa"
                        )
                    ),
                    noConnection = null
                ),
                UiState.CityGeoDataShow(
                    data = CityResponse(
                        name = "b",
                        lat = 1.2,
                        lon = 1.2,
                        country = "bb",
                        state = "bbb"
                    ),
                    noConnection =  null
                )
            )
        )
    }
    @Test
    fun test_no_connection(){
        val cityNameCityLoadResult = LoadResult.CityNameError(
            noConnection = true
        )
        val cityLiveDataWrapper = FakeLiveDataWrapper.Base()
        val liveDataWrapperUpdate : LiveDataWrapper.Update = cityLiveDataWrapper
        cityNameCityLoadResult.show(
            updateLiveData = liveDataWrapperUpdate
        )
        cityLiveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.CitiesListDataShow(
                    data = null,
                    noConnection = true
                )
            )
        )
        val cityGeoCityLoadResult = LoadResult.CityGeoError(
            noConnection = true
        )
        cityGeoCityLoadResult.show(
            updateLiveData = liveDataWrapperUpdate
        )
        cityLiveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.CitiesListDataShow(
                    data = null,
                    noConnection = true
                ),
                UiState.CityGeoDataShow(
                    data = null,
                    noConnection =  true
                )
            )
        )
    }
    @Test
    fun test_other(){
        val cityNameCityLoadResult = LoadResult.CityNameError(
            noConnection = false
        )
        val cityLiveDataWrapper = FakeLiveDataWrapper.Base()
        val liveDataWrapperUpdate : LiveDataWrapper.Update = cityLiveDataWrapper
        cityNameCityLoadResult.show(
            updateLiveData = liveDataWrapperUpdate
        )
        cityLiveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.CitiesListDataShow(
                    data = null,
                    noConnection = false
                )
            )
        )
        val cityGeoCityLoadResult = LoadResult.CityGeoError(
            noConnection = false
        )
        cityGeoCityLoadResult.show(
            updateLiveData = liveDataWrapperUpdate
        )
        cityLiveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.CitiesListDataShow(
                    data = null,
                    noConnection = false
                ),
                UiState.CityGeoDataShow(
                    data = null,
                    noConnection =  false
                )
            )
        )
    }
}