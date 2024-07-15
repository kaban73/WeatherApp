package com.example.weatherapp.repository.city

import com.example.weatherapp.core.LoadResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class CityRepositoryTest {
    @Test
    fun test_success() = runBlocking {
        val service = FakeCityService.Base()
        service.expectSuccess()
        val cityRepository = CityRepository.Base(
            service = service
        )
        val cityNameActual = cityRepository.load(
            cityName = "A"
        )
        val cityNameExpected = LoadResult.CityNameSuccess(
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
        assertEquals(cityNameExpected, cityNameActual)
        val cityGeoActual = cityRepository.load(
            latitude = 1.2,
            longitude = 1.2
        )
        val cityGeoExpected = LoadResult.CityGeoSuccess(
            CityResponse(
                name = "b",
                lat = 1.2,
                lon = 1.2,
                country = "bb",
                state = "bbb"
            )
        )
        assertEquals(cityGeoExpected, cityGeoActual)
    }
    @Test
    fun test_no_connection_exception() = runBlocking{
        val service = FakeCityService.Base()
        service.expectException(UnknownHostException())
        val cityRepository = CityRepository.Base(
            service = service
        )
        val cityNameActual = cityRepository.load(
            cityName = "A"
        )
        val cityNameExpected = LoadResult.CityNameError(
            noConnection = true
        )
        assertEquals(cityNameExpected, cityNameActual)

        val cityGeoActual = cityRepository.load(
            latitude = 1.1,
            longitude = 1.1
        )
        val cityGeoExpected = LoadResult.CityGeoError(
            noConnection = true
        )
        assertEquals(cityGeoExpected, cityGeoActual)
    }
    @Test
    fun test_other_exception() = runBlocking{
        val service = FakeCityService.Base()
        service.expectException(Exception())
        val cityRepository = CityRepository.Base(
            service = service
        )
        val cityNameActual = cityRepository.load(
            cityName = "A"
        )
        val cityNameExpected = LoadResult.CityNameError(
            noConnection = false
        )
        assertEquals(cityNameExpected, cityNameActual)

        val cityGeoActual = cityRepository.load(
            latitude = 1.1,
            longitude = 1.1
        )
        val cityGeoExpected = LoadResult.CityGeoError(
            noConnection = false
        )
        assertEquals(cityGeoExpected, cityGeoActual)
    }

}