package com.example.foroom.pages

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R

class ProfilePage {
    val navProfile = withId(R.id.homeNavigationProfile)
    val signOutItem = withId(R.id.signOutItem)
}