package com.example.bookdabu.ch2.coroutine

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

fun getResultRx() {
    // api call
    api.getData("para2", "para2")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { result ->
            updateUI(result)
        }
}

suspend fun getResultSuspend() {
    val result = api.getData("para1", "para2")
    updateUI(result)
}