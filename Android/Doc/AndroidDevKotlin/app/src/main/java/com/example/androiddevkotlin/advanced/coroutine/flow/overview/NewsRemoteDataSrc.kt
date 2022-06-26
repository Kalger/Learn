package com.example.androiddevkotlin.advanced.coroutine.flow.overview

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class NewsRemoteDataSrc(
    newsApi: NewsApi,
    refreshIntervalMs: Long = 5000,
    ioDispatcher: CoroutineDispatcher
) {
    val latestNews = flow<List<ArticleHeadline>> {
        // Executes on the IO dispatcher
        while (true) {
            val latestNews = newsApi.fetchLatestNews()
            emit(latestNews)
            delay(refreshIntervalMs)
        }
    }.flowOn(ioDispatcher)
}

interface NewsApi {
    suspend fun fetchLatestNews(): List<ArticleHeadline>
}

data class ArticleHeadline(
    val title: String = ""
)
