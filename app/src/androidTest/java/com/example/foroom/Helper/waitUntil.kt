package com.example.foroom.Helper

fun waitUntil(
    timeoutMs: Long = 10_000L,
    intervalMs: Long = 250L,
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
            Thread.sleep(intervalMs)
        }
    }
    throw lastError ?: AssertionError("Condition not met within $timeoutMs ms")
}