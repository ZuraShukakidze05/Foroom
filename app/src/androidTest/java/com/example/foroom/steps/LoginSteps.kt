package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntil
import com.example.foroom.pages.LoginPage

class LoginSteps(private val page: LoginPage = LoginPage()) {

    fun verifyLoginScreenDisplayed() = waitUntil {
        page.logInButton().check(matches(isDisplayed()))
    }

    fun verifyHomeDisplayed() = waitUntil {
        onView(withId(R.id.navBar)).check(matches(isDisplayed()))
    }

    fun loginWith(username: String, password: String) {
        verifyLoginScreenDisplayed()
        page.usernameInput().perform(replaceText(username), closeSoftKeyboard())
        page.passwordInput().perform(replaceText(password), closeSoftKeyboard())
        page.logInButton().perform(click())
    }
    fun isHomeDisplayed(): Boolean = try {
        onView(withId(R.id.navBar)).check(matches(isDisplayed()))
        true
    } catch (e: NoMatchingViewException) {
        false
    } catch (e: AssertionError) {
        false
    }

}