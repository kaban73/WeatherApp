package com.example.weatherapp

import android.widget.ImageView
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
        todayDateTextView().check(matches(isDisplayed()))
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
        checkVisibleTodayWeatherMainInfoLayout()
    }
    private fun todayWeatherMainInfoLayout() = onView(
        allOf(
            isAssignableFrom(LinearLayout::class.java),
            withId(R.id.todayWeatherMainInfoLayout),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withParent(withId(rootId))
        )
    )
    private fun checkVisibleTodayWeatherMainInfoLayout() {
        todayWeatherMainInfoLayout().check(matches(isDisplayed()))
        nowWeatherImageView().check(matches(isDisplayed()))
        nowDegreesTextView().check(matches(isDisplayed()))
        todayRangeDegreesTextView().check(matches(isDisplayed()))
        windTextView().check(matches(isDisplayed()))
        precipTextView().check(matches(isDisplayed()))
    }
    private fun nowWeatherImageView() = onView(
        allOf(
            isAssignableFrom(ImageView::class.java),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withParent(withId(R.id.todayWeatherMainInfoLayout)),
            withId(R.id.nowWeatherImageView)
        )
    )
    private fun nowDegreesTextView() = onView(
        allOf(
            isAssignableFrom(TextView::class.java),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withParent(withId(R.id.todayWeatherMainInfoLayout)),
            withId(R.id.nowDegreesTextView)
        )
    )
    fun checkTextNowDegreesTextView(degrees : String) {
        nowDegreesTextView().check(matches(withText(degrees)))
    }
    private fun todayRangeDegreesTextView() = onView(
        allOf(
            isAssignableFrom(TextView::class.java),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withParent(withId(R.id.todayWeatherMainInfoLayout)),
            withId(R.id.todayRangeDegreesTextView)
        )
    )
    fun checkTextTodayRangeDegreesTextView(degrees : String) {
        todayRangeDegreesTextView().check(matches(withText(degrees)))
    }
    private fun windTextView() = onView(
        allOf(
            isAssignableFrom(TextView::class.java),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withParent(withId(R.id.todayWeatherMainInfoLayout)),
            withId(R.id.windTextView)
        )
    )
    fun checkTextWindTextView(windInfo : String) {
        windTextView().check(matches(withText(windInfo)))
    }
    private fun precipTextView() = onView(
        allOf(
            isAssignableFrom(TextView::class.java),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withParent(withId(R.id.todayWeatherMainInfoLayout)),
            withId(R.id.precipTextView)
        )
    )
    fun checkTextPrecipTextView(precipInfo : String) {
        precipTextView().check(matches(withText(precipInfo)))
    }
}