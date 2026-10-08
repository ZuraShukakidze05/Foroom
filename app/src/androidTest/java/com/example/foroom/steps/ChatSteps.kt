package com.example.foroom.steps

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.waitUntil
import com.example.foroom.data.Constants
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage
import org.hamcrest.Matchers.allOf

class ChatSteps(
    private val create: CreateChatPage = CreateChatPage(),
    private val chats: ChatsPage = ChatsPage()
) {

    fun openChatsTab() = apply {
        waitUntil { onView(chats.navChats).perform(click()) }
    }

    fun searchChat(name: String) = apply {
        waitUntil {
            onView(allOf(withId(chats.inputEditTextId), androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA(chats.searchInput)))
                .perform(replaceText(name), closeSoftKeyboard())
        }
    }

    fun verifyChatInList(name: String) = apply {
        waitUntil { onView(chats.chatTitleMatcher(name)).check(matches(isDisplayed())) }
    }

    fun isChatInList(name: String): Boolean = try {
        waitUntil(timeoutMs = Constants.CHAT_CHECK_TIMEOUT_MS) {
            onView(chats.chatTitleMatcher(name)).check(matches(isDisplayed()))
        }
        true
    } catch (e: Throwable) {
        false
    }

    fun clickChat(name: String) = apply {
        waitUntil {
            onView(chats.chatsList).perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(allOf(withId(chats.chatTitleTextViewId), withText(name))),
                    chats.clickOpenChatButton
                )
            )
        }
    }

    fun openExistingChat(name: String) = apply {
        openChatsTab()
        searchChat(name)
        verifyChatInList(name)
        clickChat(name)
    }

    fun openCreateTab() = apply {
        waitUntil { onView(create.navCreateChat).perform(click()) }
    }

    fun enterChatName(name: String) = apply {
        waitUntil {
            onView(create.chatNameEditInput())
                .perform(replaceText(name), closeSoftKeyboard())
        }
    }

    fun chooseImage() = apply {
        waitUntil { onView(create.chatImageChooser).perform(click()) }
    }

    fun tapCreate() = apply {
        waitUntil { onView(create.createChatButton).perform(click()) }
    }

    fun verifyCreatedChatName(name: String) = apply {
        waitUntil { onView(create.chatNameTextMatcher(name)).check(matches(isDisplayed())) }
    }

    fun closeCreateChat() = apply {
        waitUntil { onView(create.closeButton).perform(click()) }
    }

    fun ensureChatExists(name: String) = apply {
        openChatsTab()
        searchChat(name)
        if (!isChatInList(name)) {
            openCreateTab()
            enterChatName(name)
            chooseImage()
            tapCreate()
            verifyCreatedChatName(name)
            closeCreateChat()
        }
    }

}