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
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.hamcrest.CoreMatchers.allOf


class TodayWeatherInfoLayout {
    private val rootId : Int = R.id.todayWeatherInfoLayout
    private fun todayWeatherRecyclerViewMatcher() = RecyclerViewMatcher(R.id.todayWeatherRecyclerView)

    private fun rootLayout() = onView(
        allOf(
            isAssignableFrom(LinearLayout::class.java),
            withId(rootId),
            withParent(isAssignableFrom(ConstraintLayout::class.java)),
            withParent(withId(R.id.weatherLayout))
        )
    )
    fun checkVisibleTodayWeatherInfoLayout() {
        rootLayout().check(matches(isDisplayed()))
    }
    private fun todayDateTextView() = onView(
        allOf(
            isAssignableFrom(TextView::class.java),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withParent(withId(rootId)),
            withId(R.id.todayDateTextView)
        )
    )
    fun checkTextTodayDateTextView(date : String) {
        todayDateTextView().check(matches(withText(date)))
    }
    private fun todayWeatherMainInfoLayout() = onView(
        isAssignableFrom(LinearLayout::class.java),
        withId(R.id.todayWeatherMainInfoLayout),
        withParent(isAssignableFrom(LinearLayout::class.java)),

    )
    private fun nowDegreesTextView() = onView(
        allOf(
            isAssignableFrom(TextView::class.java),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withParent(withId(rootId)),
            withId(R.id.nwoDegreesTextView)
        )
    )
    fun checkTextNowDegreesTextView(degrees : String) {
        nowDegreesTextView().check(matches(withText(degrees)))
    }
    private fun
}