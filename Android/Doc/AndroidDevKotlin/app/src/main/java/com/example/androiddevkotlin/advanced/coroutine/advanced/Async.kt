package com.example.androiddevkotlin.advanced.coroutine.advanced

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope

suspend fun fetchTwoDocs() =
    coroutineScope {
        val deferred1 = async { fetchDoc(1) }
        val deferred2 = async { fetchDoc(2) }
        deferred1.await()
        deferred2.await()
    }

fun fetchDoc(i: Int) {

}

suspend fun fetchTwoDocs2() =
    coroutineScope {
        val deferredList = listOf(
            async { fetchDoc(1) },
            async { fetchDoc(2) }
        )
        deferredList.awaitAll()
    }