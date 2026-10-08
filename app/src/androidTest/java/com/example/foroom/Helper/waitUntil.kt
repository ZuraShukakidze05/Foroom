package com.example.foroom.Helper
import androidx.test.espresso.Espresso

fun waitUntil(
    timeoutMs: Long = 10_000L,
    action: () -> Unit
) {
    val end = System.currentTimeMillis() + timeoutMs
    var lastError: Throwable? = null
    while (System.currentTimeMillis() < end) {
        try {
            action()
            return
        } catch (e: Throwable) {
            lastError = e
            Espresso.onIdle()
        }
    }
    throw lastError ?: AssertionError("Condition not met within $timeoutMs ms")
}

fun waitUntilOrFalse(
    timeoutMs: Long = 1_500L,
    action: () -> Unit
): Boolean = try {
    waitUntil(timeoutMs, action)
    true
} catch (e: Throwable) {
    false
}