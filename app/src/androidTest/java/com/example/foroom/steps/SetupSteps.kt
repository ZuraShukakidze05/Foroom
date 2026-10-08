package com.example.foroom.steps

import com.example.foroom.data.Constants

class SetupSteps(
    private val login: LoginSteps = LoginSteps(),
    private val profile: ProfileSteps = ProfileSteps(),
    private val chats: ChatSteps = ChatSteps(),
    private val registration: RegistrationSteps = RegistrationSteps()
) {

    fun ensureJohnWeekChatExists() = apply {
        chats.ensureChatExists(Constants.CHAT_JOHN_WEEK)
    }

    fun ensureOwnChatExists() = apply {
        chats.ensureChatExists(Constants.CHAT_OWN)
    }

    fun ensureSharedChatExists() = apply {
        chats.ensureChatExists(Constants.CHAT_SHARED)
    }

    fun prepareUsers() = apply {
        try {
            login.verifyLoginScreenDisplayed()
        } catch (e: Throwable) {
            try { profile.signOut() } catch (_: Throwable) {}
            login.verifyLoginScreenDisplayed()
        }

        val users = listOf(
            Constants.USER_A_NAME to Constants.USER_A_PASSWORD,
            Constants.USER_B_NAME to Constants.USER_B_PASSWORD
        )

        users.forEach { (user, pass) ->
            try {
                login.loginWith(user, pass)
                login.verifyHomeDisplayed()
                profile.signOut()
                login.verifyLoginScreenDisplayed()
            } catch (e: Throwable) {
                try { profile.signOut() } catch (_: Throwable) {}
                login.verifyLoginScreenDisplayed()

                registration.openRegistration()
                registration.register(user, pass)
                login.verifyHomeDisplayed()
                profile.signOut()
                login.verifyLoginScreenDisplayed()
            }
        }
    }

    fun prepareChats() = apply {
        login.loginWith(Constants.USER_A_NAME, Constants.USER_A_PASSWORD)
        login.verifyHomeDisplayed()
        ensureJohnWeekChatExists()
        ensureOwnChatExists()
        ensureSharedChatExists()
        profile.signOut()
        login.verifyLoginScreenDisplayed()
    }

    fun startClean() = apply {
        login.ensureLoggedOut(profile)
        prepareUsers()
        prepareChats()
    }
}