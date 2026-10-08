package com.example.foroom.pages

import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DsR

class CreateChatPage {
    val chatNameInput = withId(R.id.chatNameInput)
    val chatImageChooser = withId(R.id.chatImageChooser)
    val createChatButton = withId(R.id.createChatButton)
    val closeButton = withId(R.id.closeButton)
    val navCreateChat = withId(R.id.homeNavigationCreateChat)
    val inputEditTextId = DsR.id.inputEditText

    fun chatNameTextMatcher(name: String) = withText(name)

    fun chatNameEditInput() = allOf(withId(inputEditTextId), isDescendantOfA(chatNameInput))
}