package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DsR

class ChatsPage {
    val searchInput = withId(R.id.searchChatInput)
    val chatsList = withId(R.id.chatsRecyclerView)
    val navChats = withId(R.id.homeNavigationChats)
    val inputEditTextId = DsR.id.inputEditText
    val chatTitleTextViewId = DsR.id.chatTitleTextView

    fun chatTitleMatcher(name: String) = allOf(
        withId(chatTitleTextViewId),
        withText(name),
        isDescendantOfA(chatsList)
    )

    val clickOpenChatButton = object : ViewAction {
        override fun getConstraints(): Matcher<View> = isAssignableFrom(View::class.java)
        override fun getDescription() = "click open-chat button (sendMessageButton) in card"
        override fun perform(uiController: UiController, view: View) {
            click().perform(uiController, view.findViewById<View>(R.id.sendMessageButton))
        }
    }
}