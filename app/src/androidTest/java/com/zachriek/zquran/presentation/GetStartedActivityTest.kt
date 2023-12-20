package com.zachriek.zquran.presentation

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.karumi.shot.ScreenshotTest
import com.zachriek.zquran.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GetStartedActivityTest: ScreenshotTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(GetStartedActivity::class.java)

    @Test
    fun testInitialStateWithSnapshot() {
        activityRule.scenario.onActivity {
            compareScreenshot(it)
        }
    }

    @Test
    fun testViewIsVisible() {
        // make sure image is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.get_started_img),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure title is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.get_started_title),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure description is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.get_started_desc),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure button is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.get_started_button),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )
    }
}