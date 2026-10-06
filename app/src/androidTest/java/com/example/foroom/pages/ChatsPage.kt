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

class ChatsPage {
    val searchChatInput = withId(R.id.searchChatInput)
    val chatsRecyclerView = withId(R.id.chatsRecyclerView)
    val navChat = withId(R.id.homeNavigationChats)

    fun openChats() = waitUntil {
        onView(navChat).perform(click())
    }

    fun searchChat(name: String) = waitUntil {
        onView(allOf(withId(DsR.id.inputEditText), isDescendantOfA(searchChatInput)))
            .perform(replaceText(name), closeSoftKeyboard())
    }

    fun checkChatInList(name: String) = waitUntil {
        onView(
            allOf(
                withId(DsR.id.chatTitleTextView),
                withText(name),
                isDescendantOfA(chatsRecyclerView)
            )
        ).check(matches(isDisplayed()))
    }
}