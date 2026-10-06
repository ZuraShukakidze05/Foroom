package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DS

class LoginPage {
    private fun inputChild(parent: Int, child: Int) =
        onView(allOf(withId(child), isDescendantOfA(withId(parent))))

    fun logInButton() = onView(withId(R.id.logInButton))
    fun usernameInput() = inputChild(R.id.userNameInput, DS.id.inputEditText)
    fun passwordInput() = inputChild(R.id.passwordInput, DS.id.inputEditText)

}