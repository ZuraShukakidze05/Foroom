package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntil

class ProfilePage {
    val changePasswordbtn = withId(R.id.changePasswordItem)
    val changeLanguagebtn = withId(R.id.changeLanguageItem)
    val navProfile = withId(R.id.homeNavigationProfile)

    val signOutbtn = withId(R.id.signOutItem)

    fun openProfile() = waitUntil {
        onView(navProfile).perform(click())
    }

    fun changePasswordButton() = waitUntil {
        onView(changePasswordbtn).perform(click())
    }

    fun changeLanguageButton() = waitUntil {
        onView(changeLanguagebtn).perform(click())
    }

    fun checkText(text: String) = waitUntil {
        onView(withText(text)).check(matches(isDisplayed()))
    }
    fun signOutButton() = waitUntil {
        onView(signOutbtn).perform(click())
    }
}