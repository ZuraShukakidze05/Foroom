package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.Constants
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.ConversationSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import com.example.foroom.steps.SetupSteps
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConversationTests {
    @get:Rule val rule = ActivityScenarioRule(ForoomActivity::class.java)

    private val login = LoginSteps()
    private val profile = ProfileSteps()
    private val chat = ChatSteps()
    private val conv = ConversationSteps()
    private val setup = SetupSteps()

    private fun suffix() = " ${Constants.uniqueSuffix()}"

    @Before
    fun prepare() {
        setup.startClean()
    }

    private fun signInAs(user: String, pass: String) {
        login.loginWith(user, pass)
        login.verifyHomeDisplayed()
    }

    private fun switchTo(user: String, pass: String) {
        conv.closeChat()
        profile.signOut()
        login.verifyLoginScreenDisplayed()
        signInAs(user, pass)
    }

    @Test
    fun sendMessageInJohnWeekChat() {
        val msg = "${Constants.MSG_DRINK}${suffix()}"
        signInAs(Constants.USER_A_NAME, Constants.USER_A_PASSWORD)
        chat.openExistingChat(Constants.CHAT_JOHN_WEEK)
        conv.sendMessage(msg)
        conv.verifyMessage(msg)
        conv.closeChat()
        chat.openExistingChat(Constants.CHAT_JOHN_WEEK)
        conv.verifyMessage(msg)
    }

    @Test
    fun sendQuestionInOwnChat() {
        val question = "${Constants.MSG_QUESTION}${suffix()}"
        signInAs(Constants.USER_A_NAME, Constants.USER_A_PASSWORD)
        chat.openExistingChat(Constants.CHAT_OWN)
        conv.sendMessage(question)
        conv.verifyMessage(question)
    }

    @Test
    fun continueConversationWithAnotherAccount() {
        val greeting = "${Constants.MSG_GREETING}${suffix()}"
        val reply = "${Constants.MSG_REPLY}${suffix()}"

        signInAs(Constants.USER_A_NAME, Constants.USER_A_PASSWORD)
        chat.openExistingChat(Constants.CHAT_SHARED)
        conv.sendMessage(greeting)
        conv.verifyMessage(greeting)

        repeat(Constants.FILLER_COUNT) { i ->
            conv.sendMessage("${Constants.MSG_FILLER_PREFIX} $i")
        }

        switchTo(Constants.USER_B_NAME, Constants.USER_B_PASSWORD)
        chat.openExistingChat(Constants.CHAT_SHARED)
        conv.swipeToMessage(greeting)
        conv.verifyMessageFrom(greeting, Constants.USER_A_NAME)
        conv.sendMessage(reply)
        conv.verifyMessage(reply)

        switchTo(Constants.USER_A_NAME, Constants.USER_A_PASSWORD)
        chat.openExistingChat(Constants.CHAT_SHARED)
        conv.verifyMessage(reply)
        conv.verifyMessageFrom(reply, Constants.USER_B_NAME)
    }
}