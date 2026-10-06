package com.example.foroom.steps

import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ProfilePage

class ProfileSteps(
    private val profile: ProfilePage = ProfilePage(),
    private val passwordPage: ChangePasswordPage = ChangePasswordPage(),
    private val languagePage: ChangeLanguagePage = ChangeLanguagePage()
) {

    fun openProfile() {
        profile.openProfile()
    }

    fun changePassword(newPassword: String) {
        profile.changePasswordButton()
        passwordPage.enterPassword(newPassword)
        passwordPage.enterRepeatPassword(newPassword)
        passwordPage.confirm()
    }

    fun setLanguage(georgian: Boolean) {
        profile.changeLanguageButton()
        languagePage.selectLanguage(georgian)
    }

    fun verifyText(text: String) {
        profile.checkText(text)
    }
    fun signOut() {
        profile.openProfile()
        profile.signOutButton()
    }
}