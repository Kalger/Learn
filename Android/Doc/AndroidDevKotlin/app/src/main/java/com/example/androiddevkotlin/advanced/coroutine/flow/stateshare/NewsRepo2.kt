package com.example.androiddevkotlin.advanced.coroutine.flow.stateshare

import com.example.androiddevkotlin.advanced.coroutine.flow.overview.ArticleHeadline
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NewsRepo(
    private val tickHandler: TickHandler,
    private val externalScope: CoroutineScope
) {

    init {
        externalScope.launch {
            tickHandler.tickFlow.collect {
                refreshNews()
            }
        }
    }

    private suspend fun refreshNews() {
        delay(20)
    }
}
