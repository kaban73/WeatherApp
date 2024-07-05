package com.example.weatherapp

import android.widget.LinearLayout
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textfield.TextInputEditText
import org.hamcrest.CoreMatchers.allOf

class CityPage {
    private val rootId : Int = R.id.cityLayout
    private fun searchCityRecyclerViewMatcher() = RecyclerViewMatcher(R.id.cityRecyclerView)

    private fun searchEditText() = onView(
        allOf(
            withHint("Type name your city"),
            isAssignableFrom(TextInputEditText::class.java),
            withId(R.id.searchCityEditText),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withParent(withId(rootId))
        )
    )
    fun checkVisibleNowSearchEditText() {
        searchEditText().check(matches(isDisplayed()))
    }
    fun inputCityName(city : String) {
        searchEditText().perform(typeText(city), closeSoftKeyboard())
    }
    fun checkCities(position : Int, city : String) {
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(withId(R.id.cityNameTextView)),
                withParent(isAssignableFrom(TextView::class.java)),
                searchCityRecyclerViewMatcher().atPosition(position, R.id.cityNameTextView)
            )
        ).check(matches(withText(city)))
    }
    fun clickCityAt(position: Int) {
        onView(searchCityRecyclerViewMatcher().atPosition(position)).perform(click())
    }
}