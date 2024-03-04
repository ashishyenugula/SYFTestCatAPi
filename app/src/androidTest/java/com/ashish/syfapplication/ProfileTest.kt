package com.ashish.syfapplication

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.contrib.NavigationViewActions.navigateTo
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.coroutines.android.awaitFrame
import okhttp3.internal.wait
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ProfileTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun testScrollViewScrolling() {

        onView(withId(R.id.scroll_home)).perform(swipeUp())
            .check(matches(isDisplayed())) // Scroll up
    }

    @Test
    fun navigateToMenuItem() {


        // Open the navigation drawer
        onView(withId(R.id.drawer_layout)).check(matches(DrawerMatchers.isClosed()))
            .perform(DrawerActions.open())
            .check(matches(DrawerMatchers.isOpen()))


        // Click on the desired menu item
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_gallery))
        // Perform actions to trigger RecyclerView loading
        onView(withId(R.id.rv_random_cat_images))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

        onView(withId(R.id.idPBLoading)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(withId(R.id.idPBLoading)).check(matches((withEffectiveVisibility(ViewMatchers.Visibility.GONE))))


    }

    @Test
    fun navigateToCatDetails() {

        // Open the navigation drawer
        onView(withId(R.id.drawer_layout)).check(matches(DrawerMatchers.isClosed()))
            .perform(DrawerActions.open())
            .check(matches(DrawerMatchers.isOpen()))


        // Click on the desired menu item
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_gallery))
        // Perform actions to trigger RecyclerView loading
        onView(withId(R.id.rv_random_cat_images))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.idPBLoading)).check(matches(isDisplayed()));
        onView(withId(R.id.idPBLoading)).check(matches(not(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))))

        // Perform other actions you want to test
        // For example, click on an item in the RecyclerView
        onView(withId(R.id.rv_random_cat_images))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    5,
                    click()
                )
            ).check(matches(isDisplayed()))

        val expectedText = "Temperament"
        onView(withId(R.id.cat_temp_text)).check(matches(withText(expectedText)))

    }


}