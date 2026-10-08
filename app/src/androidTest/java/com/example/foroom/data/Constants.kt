package com.example.foroom.data

object Constants {

    const val USER_A_NAME = "student"
    const val USER_A_PASSWORD = "Student123!"
    const val USER_B_NAME = "userb_test"
    const val USER_B_PASSWORD = "PassB123!"

    const val CHAT_JOHN_WEEK = "johnWeek"
    const val CHAT_OWN = "Zura Shukakidze"
    const val CHAT_SHARED = "something"

    const val MSG_DRINK = "let's go for a drink"
    const val MSG_QUESTION = "Which module do you like most in the Automation Academy?"
    const val MSG_GREETING = "Hello from A"
    const val MSG_REPLY = "Hello back from B"
    const val MSG_FILLER_PREFIX = "filler"

    const val FILLER_COUNT = 25
    const val MAX_SWIPES = 15
    const val DEFAULT_TIMEOUT_MS = 5_000L
    const val SHORT_TIMEOUT_MS = 1_500L
    const val CHAT_CHECK_TIMEOUT_MS = 3_000L
    const val SWIPE_DURATION_MS = 300
    const val SWIPE_START_RATIO = 0.25
    const val SWIPE_END_RATIO = 0.75

    fun uniqueSuffix(): String = System.currentTimeMillis().toString().takeLast(6)
}