package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import com.example.foroom.Helper.waitUntil
import com.example.foroom.pages.ProfilePage

class ProfileSteps(private val page: ProfilePage = ProfilePage()) {

    fun openProfile() = apply {
        waitUntil { onView(page.navProfile).perform(click()) }
    }

    fun tapSignOut() = apply {
        waitUntil { onView(page.signOutItem).perform(click()) }
    }

    fun signOut() = apply {
        openProfile()
        tapSignOut()
    }
}