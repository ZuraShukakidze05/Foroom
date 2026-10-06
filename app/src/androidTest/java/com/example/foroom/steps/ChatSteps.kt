package com.example.foroom.steps

import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage

class ChatSteps(
    private val createChatPage: CreateChatPage = CreateChatPage(),
    private val chatsPage: ChatsPage = ChatsPage()
) {

    fun createChat(name: String) {
        createChatPage.openChat()
        createChatPage.enterChatName(name)
        createChatPage.imageChooser()
        createChatPage.tapCreateChat()
        createChatPage.checkChatName(name)
    }

    fun findChatInList(name: String) {
        createChatPage.closeChat()
        chatsPage.openChats()
        chatsPage.searchChat(name)
        chatsPage.checkChatInList(name)
    }
}