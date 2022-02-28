package com.example.bookdabu.ch2.coroutine

import kotlinx.coroutines.*

fun caseLaunch() {
    GlobalScope.launch { delay(500) }
}

suspend fun caseAsync() {
    val deferred = GlobalScope.async {
        delay(500)
        return@async listOf<String>()
    }
    val list = deferred.await()
}

suspend fun caseWithContext() {
    val list = withContext(Dispatchers.IO) {
        delay(500)
        return@withContext listOf<String>()
    }
}

suspend fun caseCoroutineScopeFun() {
    val list = coroutineScope {
        delay(500)
        return@coroutineScope listOf<String>()
    }
}

fun getList(): List<String> = runBlocking {
    delay(500)
    return@runBlocking listOf<String>()
}