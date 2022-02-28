package com.example.bookdabu.ch2.coroutine

import kotlinx.coroutines.delay

suspend fun updateWithDelay() {
    delay(5_000)
    update()
}