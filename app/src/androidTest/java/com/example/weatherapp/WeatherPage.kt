package com.example.weatherapp

import android.graphics.Bitmap
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
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withResourceName
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import org.hamcrest.CoreMatchers.allOf

class WeatherPage {
    private val rootId : Int = R.id.weatherLayout
    private val todayWeatherInfoLayout = TodayWeatherInfoLayout()
    private fun futureWeatherRecyclerViewMatcher() = RecyclerViewMatcher(R.id.futureWeatherRecyclerView)
    private fun todayWeatherRecyclerViewMatcher() = RecyclerViewMatcher(R.id.todayWeatherRecyclerView)

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
    fun checkVisibleTodayWeatherInfoLayout() =
        todayWeatherInfoLayout.checkVisibleTodayWeatherInfoLayout()
    fun checkWeatherToday(position : Int, image : Bitmap, time : String, degrees : String) {
        val weatherTodayLayout : Int = R.id.todayWeatherLayout
        onView(
            allOf(
                isAssignableFrom(ImageView::class.java),
                withParent(withId(weatherTodayLayout)),
                withParent(isAssignableFrom(LinearLayout::class.java)),
                todayWeatherRecyclerViewMatcher().atPosition(position, R.id.todayWeatherIamgeView)
            )
        ).check(matches(withResourceName(image.toString())))
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(withId(weatherTodayLayout)),
                withParent(isAssignableFrom(LinearLayout::class.java)),
                todayWeatherRecyclerViewMatcher().atPosition(position, R.id.todayWeatherTimeTextView)
            )
        ).check(matches(withText(time)))
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(withId(weatherTodayLayout)),
                withParent(isAssignableFrom(LinearLayout::class.java)),
                todayWeatherRecyclerViewMatcher().atPosition(position, R.id.todayWeatherDegreesTextView)
            )
        ).check(matches(withText(degrees)))
    }
    fun checkWeatherFuture(position: Int, image: Bitmap, date : String, rangeDegrees : String) {
        val weatherFutureLayout : Int = R.id.futureWeatherLayout
        onView(
            allOf(
                isAssignableFrom(ImageView::class.java),
                withParent(withId(weatherFutureLayout)),
                withParent(isAssignableFrom(ConstraintLayout::class.java)),
                futureWeatherRecyclerViewMatcher().atPosition(position, R.id.futureWeatherImageView)
            )
        ).check(matches(withResourceName(image.toString())))
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(withId(weatherFutureLayout)),
                withParent(isAssignableFrom(ConstraintLayout::class.java)),
                futureWeatherRecyclerViewMatcher().atPosition(position, R.id.futureWeatherDateTextView)
            )
        ).check(matches(withText(date)))
        onView(
            allOf(
                isAssignableFrom(TextView::class.java),
                withParent(withId(weatherFutureLayout)),
                withParent(isAssignableFrom(ConstraintLayout::class.java)),
                futureWeatherRecyclerViewMatcher().atPosition(position, R.id.futureWeatherRangeDegreesTextView)
            )
        ).check(matches(withText(rangeDegrees)))
    }
}