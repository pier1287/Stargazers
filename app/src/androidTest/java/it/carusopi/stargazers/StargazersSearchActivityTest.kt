package it.carusopi.stargazers

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.times
import android.support.test.espresso.intent.matcher.BundleMatchers
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.hasErrorText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import bolts.Task.delay
import it.carusopi.stargazers.list.StargazersListActivity
import it.carusopi.stargazers.search.StargazersSearchActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by carusopi on 30/10/2017.
 */

@RunWith(AndroidJUnit4::class)
class StargazersSearchActivityTest {


    @Rule
    @JvmField
    val searchActivityTestRule = ActivityTestRule(StargazersSearchActivity::class.java)


    @Test
    fun ownerEditText_shouldShowError_whenSearchBtnIsClickedAndValueIsBlank() {
        searchActivityTestRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withId(R.id.btnSearch)).perform(click())
        delay(1000)

        Espresso.onView(ViewMatchers.withId(R.id.etextOwner))
                .check(matches(hasErrorText("Please enter owner")))
    }


    @Test
    fun repositoryEditText_shouldShowError_whenSearchBtnIsClickedAndValueIsBlank() {
        searchActivityTestRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withId(R.id.btnSearch)).perform(click())
        delay(1000)

        Espresso.onView(ViewMatchers.withId(R.id.etextRepository))
                .check(matches(hasErrorText("Please enter repository")))
    }


    @Test
    fun newIntent_shouldContainStargazersListActivity_whenSearchBtnIsClicked() {
        val expectedOwner = "expected_owner"
        val expectedRepository = "expected_repository"

        searchActivityTestRule.launchActivity(Intent())
        delay(1000)

        Espresso.onView(ViewMatchers.withId(R.id.etextOwner)).perform(ViewActions.replaceText(expectedOwner), ViewActions.closeSoftKeyboard())
        delay(1000)

        Espresso.onView(ViewMatchers.withId(R.id.etextRepository)).perform(ViewActions.replaceText(expectedRepository), ViewActions.closeSoftKeyboard())
        delay(1000)

        Intents.init()

        Espresso.onView(ViewMatchers.withId(R.id.btnSearch)).perform(click())
        delay(1000)

        Intents.intended(hasComponent(StargazersListActivity::class.java.name), times(1))
        Intents.intended(hasExtras(BundleMatchers.hasValue(expectedOwner)))
        Intents.intended(hasExtras(BundleMatchers.hasValue(expectedRepository)))
        Intents.release()
    }

}