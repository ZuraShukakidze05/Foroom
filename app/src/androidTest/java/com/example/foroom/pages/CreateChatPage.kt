package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntil
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DsR

class CreateChatPage {
    val chatNameInput = withId(R.id.chatNameInput)
    val chatImageChooser = withId(R.id.chatImageChooser)
    val createChatButton = withId(R.id.createChatButton)
    val closeButton = withId(R.id.closeButton)
    val navCreateChat = withId(R.id.homeNavigationCreateChat)

    fun openChat() = waitUntil{
        onView(navCreateChat).perform(click())
    }
    fun enterChatName(name: String) = waitUntil {
        onView(allOf(withId(DsR.id.inputEditText), isDescendantOfA(chatNameInput)))
            .perform(replaceText(name), closeSoftKeyboard())
    }
    fun imageChooser() = waitUntil {
        onView(chatImageChooser).perform(click())
    }
    fun tapCreateChat() = waitUntil {
        onView(createChatButton).perform(click())
    }
    fun closeChat() = waitUntil {
        onView(closeButton).perform(click())
    }
    fun checkChatName(name: String) = waitUntil {
        onView(withText(name)).check(matches(isDisplayed()))
    }

}