package gojek.assignment.com.gojek_androidassignment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import gojek.assignment.com.gojek_androidassignment.fragments.FragmentError
import org.junit.Rule
import org.junit.Before
import androidx.test.InstrumentationRegistry.getTargetContext
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.matcher.ViewMatchers.*
import gojek.assignment.com.gojek_androidassignment.fragments.WeatherInfoFragment
import gojek.assignment.com.gojek_androidassignment.viewmodels.MainWeatherViewModel
import org.hamcrest.Matchers.not
import org.mockito.Mockito.mock


/**
 * Created by Akul Aggarwal on 04/08/19.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    val viewModel = mock(MainWeatherViewModel::class.java)



    @Before
    fun init() {
//        mActivityRule.activity
//            .supportFragmentManager.beginTransaction().replace()
        //  mActivityRule.launchActivity(Intent())

    }


    @Test
    fun checkLoaderImage() {
        onView(withId(R.id.imgLoading)).check(matches(isDisplayed()))
    }

    @Test
    fun checkWeatherInfo() {

        onView(withId(R.id.textCurrentWeather)).check(matches(not(withText(""))))
        onView(withId(R.id.textCurrentCity)).check(matches(not(withText(""))))
    }

    @Test
    fun errorFragmentTest() {
        val fragment = FragmentError()
        mActivityRule.activity.supportFragmentManager.beginTransaction()
            .replace(R.id.containerFrags, fragment).commit()
        onView(withId(R.id.textError)).check(matches(withText(R.string.something_wrong)))
        onView(withId(R.id.buttonRetry)).check(matches(withText(R.string.retry)))
    }

}