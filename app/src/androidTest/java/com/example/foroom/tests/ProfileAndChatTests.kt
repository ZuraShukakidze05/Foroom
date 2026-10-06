package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.Constants.ENGLISH_LABEL
import com.example.foroom.data.Constants.EXISTING_USERNAME
import com.example.foroom.data.Constants.FULL_NAME
import com.example.foroom.data.Constants.GEORGIAN_LABEL
import com.example.foroom.data.Constants.NEW_PASSWORD
import com.example.foroom.data.Constants.VALID_PASSWORD
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    @get:Rule
    val rule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    @Before
    fun setUp() {
        if (loginSteps.isHomeDisplayed()) {
            profileSteps.signOut()
        }
        loginSteps.loginWith(EXISTING_USERNAME, VALID_PASSWORD)
        loginSteps.verifyHomeDisplayed()
    }

    @Test
    fun changePasswordAndVerify() {
        profileSteps.openProfile()
        profileSteps.changePassword(NEW_PASSWORD)
        loginSteps.verifyLoginScreenDisplayed()

        loginSteps.loginWith(EXISTING_USERNAME, NEW_PASSWORD)
        loginSteps.verifyHomeDisplayed()

        profileSteps.openProfile()
        profileSteps.changePassword(VALID_PASSWORD)
        loginSteps.verifyLoginScreenDisplayed()
    }

    @Test
    fun changeLanguageGeorgianToEnglishAndBack() {
        profileSteps.openProfile()
        profileSteps.setLanguage(georgian = true)
        profileSteps.verifyText(GEORGIAN_LABEL)
        profileSteps.openProfile()
        profileSteps.setLanguage(georgian = false)
        profileSteps.verifyText(ENGLISH_LABEL)
        profileSteps.openProfile()
        profileSteps.setLanguage(georgian = true)
        profileSteps.verifyText(GEORGIAN_LABEL)
    }

    @Test
    fun createChatAndFindInList() {
        val chatName = "$FULL_NAME ${System.currentTimeMillis()}"
        chatSteps.createChat(chatName)
        chatSteps.findChatInList(chatName)
    }
}