package com.example.weatherapp

import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import org.hamcrest.CoreMatchers.allOf

class WeatherPage {
    private val rootId : Int = R.id.weatherLayout
    private fun futureWeatherRecyclerViewMatcher() = RecyclerViewMatcher(R.id.futureWeatherRecyclerView)

    private fun searchEditText() = onView(
        allOf(
            withHint("Change your city"),
            isAssignableFrom(TextInputEditText::class.java),
            withId(R.id.searchCityEditText),
            withParent(isAssignableFrom(ConstraintLayout::class.java)),
            withParent(withId(rootId))
        )
    )

    fun searchEditTextCheckVisibleNow() {
        searchEditText().check(matches(isDisplayed()))
    }
    fun clickSearchEditText() {
        searchEditText().perform(click())
    }
}