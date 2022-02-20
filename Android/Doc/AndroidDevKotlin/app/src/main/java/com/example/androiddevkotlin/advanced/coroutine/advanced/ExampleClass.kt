package com.example.androiddevkotlin.advanced.coroutine.advanced

import kotlinx.coroutines.*

class ExampleClass {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun exampleMethod() {
        val job = scope.launch {
            fetchDocs()
        }
        val somethingHappen = true
        if (somethingHappen) {
            job.cancel()
        }
    }

    fun exampleCoroutineContext() {
        val job1 = scope.launch {  }
        val job2 = scope.launch(Dispatchers.Default + CoroutineName("BackgroundCoroutine")) {

        }
    }

    fun cleanUp() {
        scope.cancel()
    }

}