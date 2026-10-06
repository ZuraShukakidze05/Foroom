package com.example.foroom.data

object Constants {
    const val EXISTING_USERNAME = "student"
    const val VALID_PASSWORD = "Student123!"
    const val NEW_PASSWORD = "NewStudent123!"
    const val FULL_NAME = "Zura Shukakidze"
    const val GEORGIAN_LABEL = "ენის შეცვლა"
    const val ENGLISH_LABEL = "Change Language"

    fun uniqueUsername(prefix: String) = "$prefix${System.currentTimeMillis()}"
}