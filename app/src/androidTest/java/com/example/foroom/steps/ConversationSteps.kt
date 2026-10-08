package com.example.foroom.steps

import android.graphics.Rect
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.swiper
import com.example.foroom.Helper.waitUntil
import com.example.foroom.Helper.waitUntilOrFalse
import com.example.foroom.data.Constants
import com.example.foroom.pages.ConversationPage
import org.hamcrest.Matcher

class ConversationSteps(private val page: ConversationPage = ConversationPage()) {

    fun typeMessage(text: String) = apply {
        waitUntil { onView(page.messageInput).perform(replaceText(text), closeSoftKeyboard()) }
    }

    fun tapSend() = apply {
        waitUntil { onView(page.sendButton).perform(click()) }
    }

    private fun assertMessageNow(text: String) = apply {
        onView(page.messageTextMatcher(text)).check(matches(isDisplayed()))
    }

    fun verifyMessage(text: String) = apply {
        waitUntil { assertMessageNow(text) }
    }

    private fun isMessageVisible(text: String): Boolean = try {
        assertMessageNow(text)
        true
    } catch (e: Throwable) {
        false
    }

    fun isMessageVisibleWithin(text: String, timeoutMs: Long = Constants.SHORT_TIMEOUT_MS): Boolean =
        waitUntilOrFalse(timeoutMs) { assertMessageNow(text) }

    fun verifyMessageFrom(text: String, sender: String) = apply {
        waitUntil {
            onView(page.messageWithSenderMatcher(text, sender)).check(matches(isDisplayed()))
        }
    }

    private fun listBounds(): Rect {
        var bounds = Rect()
        onView(page.messageList).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> = isDisplayed()
            override fun getDescription() = "read on-screen bounds of the message list"
            override fun perform(uiController: UiController, view: View) {
                val loc = IntArray(2)
                view.getLocationOnScreen(loc)
                bounds = Rect(loc[0], loc[1], loc[0] + view.width, loc[1] + view.height)
            }
        })
        return bounds
    }

    fun swipeToOlderMessages() = apply {
        val r = listBounds()
        val start = r.top + (r.height() * Constants.SWIPE_START_RATIO).toInt()
        val end = r.top + (r.height() * Constants.SWIPE_END_RATIO).toInt()
        swiper(start, end, Constants.SWIPE_DURATION_MS)
    }

    fun closeChat() = apply {
        waitUntil { onView(page.closeButton).perform(click()) }
    }

    fun sendMessage(text: String) = apply {
        typeMessage(text)
        tapSend()
        verifyMessage(text)
    }

    fun swipeToMessage(text: String, maxSwipes: Int = Constants.MAX_SWIPES) = apply {
        var swipes = 0
        while (swipes < maxSwipes && !isMessageVisibleWithin(text)) {
            swipeToOlderMessages()
            swipes++
        }
        verifyMessage(text)
    }
}