package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntil
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DsR

class ChangePasswordPage {
    val passwordInput = withId(R.id.passwordInput)
    val repeatPasswordInput = withId(R.id.repeatPasswordInput)
    val actionButton = withId(DsR.id.actionButton)

    fun enterPassword(password: String) = waitUntil {
        onView(allOf(withId(DsR.id.inputEditText), isDescendantOfA(passwordInput)))
            .perform(replaceText(password), closeSoftKeyboard())
    }

    fun enterRepeatPassword(password: String) = waitUntil {
        onView(allOf(withId(DsR.id.inputEditText), isDescendantOfA(repeatPasswordInput)))
            .perform(replaceText(password), closeSoftKeyboard())
    }

    fun confirm() = waitUntil {
        onView(actionButton).perform(click())
    }
}