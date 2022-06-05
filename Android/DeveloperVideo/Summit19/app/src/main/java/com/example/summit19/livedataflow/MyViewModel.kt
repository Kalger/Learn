package com.example.summit19.livedataflow

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


/**
 * https://www.youtube.com/watch?v=B8ppnjGPAGE
 * https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/724076/
 */
class MyViewModel(
    private val repo: MyRepo
) : ViewModel() {

    private val _itemId = MutableLiveData<String>()

    val result2 = _itemId.switchMap {
        liveData { emit(fetchItem(it)) }
    }

    val result3 = liveData { emit(repo.fetchWeather()) }

    private fun fetchItem(it: String): String {
        return ""
    }

    val result = liveData {
        emit(doComputation())
    }

    val currentWeather: LiveData<String> = repo.fetchWeather()

    val weatherSrcFlow: LiveData<String> = liveData {
        repo.fetchWeatherFlow().collect { emit(it) }
    }

    val weatherSrcFlow2: LiveData<String> = repo.fetchWeatherFlow().asLiveData()

    val weatherSrcFlow3: LiveData<String> = liveData {
        emit(LOANING)
        emitSource(repo.fetchWeatherFlow().asLiveData())
    }

    val weatherSrcFlow4: LiveData<String> = repo.fetchWeatherFlow()
        .onStart { emit(LOANING) }
        .asLiveData()

    val weatherSrcLiveData: LiveData<String> = repo.fetchWeather().switchMap {
        liveData { emit(heavyTransformation(it)) }
    }

    val weatherSrcFlow5: LiveData<String> = repo.fetchWeatherFlow()
        .map { heavyTransformation(it) }
        .asLiveData()

    private suspend fun heavyTransformation(it: String): String {
        delay(200)
        return "" + 1
    }

    private fun doComputation(): String {
        return ""
    }

    fun caseLivedataBuilder() {
        liveData<String>(Dispatchers.IO) {
            emit(LOANING)
            emitSource(repo.fetchWeather())
        }
    }

    fun tmp() {
        runBlocking {
            val job1 = launch {

            }
            val job2 = launch {

            }
        }
    }

    companion object {
        const val LOANING = "loading"
    }
}