package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.waitUntil
import com.example.foroom.data.Constants
import com.example.foroom.pages.LoginPage

class LoginSteps(private val page: LoginPage = LoginPage()) {

    fun verifyLoginScreenDisplayed() = apply {
        waitUntil { onView(page.logInButton).check(matches(isDisplayed())) }
    }

    fun verifyHomeDisplayed() = apply {
        waitUntil { onView(page.navBar).check(matches(isDisplayed())) }
    }

    fun isLoginDisplayed(): Boolean = try {
        onView(page.logInButton).check(matches(isDisplayed()))
        true
    } catch (e: Throwable) {
        false
    }

    fun isHomeDisplayed(): Boolean = try {
        onView(page.navBar).check(matches(isDisplayed()))
        true
    } catch (e: Throwable) {
        false
    }

    fun typeUsername(user: String) = apply {
        waitUntil {
            onView(page.input(page.userNameInputId)).perform(replaceText(user), closeSoftKeyboard())
        }
    }

    fun typePassword(password: String) = apply {
        waitUntil {
            onView(page.input(page.passwordInputId)).perform(replaceText(password), closeSoftKeyboard())
        }
    }

    fun tapLogIn() = apply {
        waitUntil { onView(page.logInButton).perform(click()) }
    }

    fun loginWith(user: String, password: String) = apply {
        verifyLoginScreenDisplayed()
        typeUsername(user)
        typePassword(password)
        tapLogIn()
    }

    fun waitForLoginOrHome() = apply {
        waitUntil(Constants.DEFAULT_TIMEOUT_MS) {
            if (!isLoginDisplayed() && !isHomeDisplayed()) {
                throw AssertionError("Neither login nor home screen is displayed yet")
            }
        }
    }

    fun ensureLoggedOut(profile: ProfileSteps = ProfileSteps()) = apply {
        waitForLoginOrHome()
        if (isHomeDisplayed()) {
            profile.signOut()
        }
        verifyLoginScreenDisplayed()
    }
}