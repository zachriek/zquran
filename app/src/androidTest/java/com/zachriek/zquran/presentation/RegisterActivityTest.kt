package com.zachriek.zquran.presentation

import androidx.test.espresso.Espresso
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
class RegisterActivityTest: ScreenshotTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(RegisterActivity::class.java)

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
            ViewMatchers.withId(R.id.register_title),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            ),
        )

        // make sure description is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.register_desc),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            ),
        )

        // make sure text input edit text name is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.register_ti_name),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            ),
        )

        // make sure text input edit text username is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.register_ti_username),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            ),
        )

        // make sure text input edit text email is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.register_ti_email),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            ),
        )

        // make sure text input edit text phone is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.register_ti_phone),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            ),
        )

        // make sure text input edit text password is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.register_ti_password),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            ),
        )

        // make sure button is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.register_button),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            ),
        )

        // make sure text is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.register_login_text),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            ),
        )

        // make sure text is shown
        Espresso.onView(
            ViewMatchers.withId(R.id.register_login_text_2),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            ),
        )
    }

    @Test
    fun testViewIsInvisible() {
        // make sure loading layout is invisible
        Espresso.onView(
            ViewMatchers.withId(R.id.register_loading_layout),
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)
            ),
        )
    }
}