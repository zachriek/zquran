package com.zachriek.zquran.presentation

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zachriek.zquran.R
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest: ScreenshotTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testInitialStateWithSnapshot() {
        activityRule.scenario.onActivity {
            compareScreenshot(it)
        }
    }

    @Test
    fun testViewIsVisible() {
        // make sure title is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.login_title),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure description is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.login_desc),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure text input edit text username is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.login_ti_username),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure text input edit text password is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.login_ti_password),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure button is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.login_button),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure text is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.login_register_text),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )

        // make sure text is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.login_register_text_2),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed(),
            ),
        )
    }

    @Test
    fun testViewIsInvisible() {
        // make sure loading layout is invisible
        Espresso.onView(
            ViewMatchers.withId(R.id.login_loading_layout),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)
            ),
        )
    }

    @Test
    fun testButtonIsClicked() {
        // make sure loading layout is visible after text input edit text username filled
        // text input edit text password filled and button is clicked
        Espresso.onView(
            ViewMatchers.withId(R.id.login_ti_username),
        ).perform(
            ViewActions.typeText("zachriek"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.login_ti_password),
        ).perform(
            ViewActions.typeText("password"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.login_button),
        ).perform(
            ViewActions.click()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.login_loading_layout),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            ),
        )
    }
}