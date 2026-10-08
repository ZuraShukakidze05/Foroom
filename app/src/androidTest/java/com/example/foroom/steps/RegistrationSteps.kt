package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.waitUntil
import com.example.foroom.pages.LoginPage
import com.example.foroom.pages.RegistrationPage

class RegistrationSteps(
    private val login: LoginPage = LoginPage(),
    private val page: RegistrationPage = RegistrationPage()
) {
    fun openRegistration() = apply {
        waitUntil { onView(login.signUpButton).perform(click()) }
        registrationScreenShown()
    }

    fun registrationScreenShown() = apply {
        waitUntil { onView(page.repeatPasswordMatcher()).check(matches(isDisplayed())) }
    }

    fun typeUsername(user: String) = apply {
        waitUntil {
            onView(page.input(page.userNameInputId)).perform(replaceText(user), closeSoftKeyboard())
        }
    }

    fun typePassword(pass: String) = apply {
        waitUntil {
            onView(page.input(page.passwordInputId)).perform(replaceText(pass), closeSoftKeyboard())
        }
    }

    fun typeRepeatPassword(pass: String) = apply {
        waitUntil {
            onView(page.input(page.repeatPasswordInputId)).perform(replaceText(pass), closeSoftKeyboard())
        }
    }

    fun tapAvatar(pos: Int = 0) = apply {
        waitUntil {
            onView(page.avatarOption(pos)).perform(click())
        }
    }

    fun tapSignUp() = apply {
        waitUntil { onView(page.signUpButton).perform(click()) }
    }

    fun register(user: String, pass: String) = apply {
        typeUsername(user)
        typePassword(pass)
        typeRepeatPassword(pass)
        tapAvatar(0)
        tapSignUp()
    }
}