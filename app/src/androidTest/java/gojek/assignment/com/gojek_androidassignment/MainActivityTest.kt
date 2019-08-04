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
import androidx.test.espresso.matcher.ViewMatchers.*
import junit.framework.Assert.assertTrue
import org.hamcrest.Matchers.not
import androidx.test.rule.GrantPermissionRule
import gojek.assignment.com.gojek_androidassignment.RecyclerViewItemCountAssertion.withItemCount
import phonepe.interview.com.dunzo.utils.constants


/**
 * Created by Akul Aggarwal on 04/08/19.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)


    @Rule
    @JvmField
    val grantPermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)


    @Before
    fun init() {
    }


    @Test
    fun checkWeatherInfo() {
        onView(withId(R.id.textCurrentWeather)).check(matches(not(withText(""))))
        onView(withId(R.id.textCurrentCity)).check(matches(not(withText(""))))
        onView(withId(R.id.listForecast)).check(withItemCount(constants.DAYS))
    }

    @Test
    fun errorFragmentTest() {
        val fragment = FragmentError()
        mActivityRule.activity.supportFragmentManager.beginTransaction()
            .replace(R.id.containerFrags, fragment).commit()
        onView(withId(R.id.textError)).check(matches(withText(R.string.something_wrong)))
        onView(withId(R.id.buttonRetry)).check(matches(withText(R.string.retry)))
        onView(withId(R.id.buttonRetry)).perform(click())
        assertTrue(!fragment.isVisible)
    }

}