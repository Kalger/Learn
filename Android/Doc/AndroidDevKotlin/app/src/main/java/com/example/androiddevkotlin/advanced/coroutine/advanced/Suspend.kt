package com.example.androiddevkotlin.advanced.coroutine.advanced

import android.app.ProgressDialog.show
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun fetchDocs() {                             // Dispatchers.Main
    val result = get("https://developer.android.com") // Dispatchers.IO for `get`
    show(result)                                      // Dispatchers.Main
}

suspend fun get(url: String) = withContext(Dispatchers.IO) {
    return@withContext ""
}

fun show(str: String) {

}