package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DS

class LoginPage {
    val logInButton = withId(R.id.logInButton)
    val navBar = withId(R.id.navBar)

    val userNameInputId = withId(R.id.userNameInput)
    val passwordInputId = withId(R.id.passwordInput)

    val inputEditTextId = DS.id.inputEditText

    val signUpButton = withId(R.id.signUpButton)

    fun input(parentMatcher: Matcher<View>) =
        allOf(withId(inputEditTextId), isDescendantOfA(parentMatcher))
}