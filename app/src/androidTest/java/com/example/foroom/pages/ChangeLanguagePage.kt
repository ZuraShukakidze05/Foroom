package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntil

class ChangeLanguagePage {
    val languageButtonGeo = withId(R.id.languageButtonGeo)
    val languageButtonEng = withId(R.id.languageButtonEng)

    fun selectLanguage(georgian: Boolean) = waitUntil {
        onView(if (georgian) languageButtonGeo else languageButtonEng)
            .perform(click())
    }
}