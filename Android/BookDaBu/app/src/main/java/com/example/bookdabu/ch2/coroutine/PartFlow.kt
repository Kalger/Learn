package com.example.bookdabu.ch2.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun demoFlow(): Flow<Int> = flow {
    // A code
    for (i in 1..3) {
        delay(100)

        // 呼叫後會執行 B code
        emit(i)
    }
}

fun main() = runBlocking {
    // 呼叫 collect 就是在執行 A code
    demoFlow().collect {
        // B code
        print(it)
    }
}

fun caseMap() {
    val mappedFlow = demoFlow().map { it.toString() }

    runBlocking {
        // 將 demoFlow 的值傳成 string 印出
        mappedFlow.collect {
            print(it)
        }
    }
}

fun caseFlowOn() {
    val flow = flow {
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }.flowOn(Dispatchers.Default)
}


suspend fun caseCatch() {
    suspend fun FlowCollector<Int>.emitData() {
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    flow { emitData() }
        .map {computeOne(it)}
        .catch {  }
        .map { computeTwo(it) }
        .collect { process(it) }
}