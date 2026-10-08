package com.example.foroom.pages

import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DsR

class ConversationPage {
    val titleView = withId(DsR.id.chatNameTextView)
    val messageInput = allOf(
        withId(DsR.id.inputEditText),
        isDescendantOfA(withId(R.id.messageInput))
    )
    val sendButton = allOf(withId(R.id.sendMessageButton), isDisplayed())
    val messageList = withId(R.id.messagesRecyclerView)
    val messageRow = withId(DsR.id.contentLinearLayout)
    val closeButton = withId(R.id.closeButton)

    fun messageTextMatcher(text: String) = allOf(withText(text), isDescendantOfA(messageList))

    fun messageWithSenderMatcher(text: String, sender: String) =
        allOf(messageRow, hasDescendant(withText(text)), hasDescendant(withText(sender)))
}