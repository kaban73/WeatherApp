package com.example.weatherapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherAppTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_launch_app_without_internet_connection(){
        // check main error
        val weatherPage = WeatherPage()
        weatherPage.checkVisibleNow()
        weatherPage.checkEmptyWeatherToday()
        weatherPage.checkEmptyWeatherFuture()

        // check empty recyclerViews
        // check error find city
    }
    @Test
    fun test_launch_app_without_geo() {
        // check main error
        // check empty recyclerViews
        // check find city, choose city and comeback
    }
    @Test
    fun test_launch_app_without_problems() {
        // check not empty recyclerViews
        // change city and comeback
    }
}