package com.example.foroom.pages

import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withParentIndex
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DS

class RegistrationPage {
    val userNameInputId = R.id.userNameInput
    val passwordInputId = R.id.passwordInput
    val repeatPasswordInputId = R.id.repeatPasswordInput
    val signUpButton = valSignUpButton()

    fun valSignUpButton() = withId(R.id.signUpButton)

    fun repeatPasswordMatcher() = withId(R.id.repeatPasswordInput)

    fun input(parent: Int) =
        allOf(withId(DS.id.inputEditText), isDescendantOfA(withId(parent)))

    fun avatarOption(pos: Int = 0) =
        allOf(withParent(withId(R.id.listView)), withParentIndex(pos), isDisplayed())
}