package com.example.summit19.livedataflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class MyRepo(
    private val src: MySrc
) {

    val weatherFlow = src.fetchWeatherFlow()
        .map { "" }
        .filter { it == "" }
        .dropWhile { it == "no" }
        .combine(src.fetchWeatherFlow2()) { a, b -> "" }
        .flowOn(Dispatchers.IO) // 改變 context : 上面的 flow operator 都會跑在 IO
        .onCompletion {  } // invokes the given action after the flow is completed or cancelled,

    fun fetchWeather(): LiveData<String> {
        return liveData { emit("") }
    }

    suspend fun printLog() {
        while (true) {
            delay(1000)
        }
    }

    fun fetchWeatherFlow() = flow<String> {  }
}