package com.example.bookdabu.ch2.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

suspend fun caseGetResultSuspend() {
    val result = api.getData("para1", "para2")
    updateUI(result)
}

suspend fun Api.getData(para1: String, para2: String) = withContext(Dispatchers.IO){
    delay(500)
    return@withContext listOf<String>()
}

suspend fun updateUI(list: List<String>) = withContext(Dispatchers.Main) {

}