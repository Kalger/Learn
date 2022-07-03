package com.example.androiddevkotlin.advanced.coroutine.flow.stateshare

import com.example.androiddevkotlin.advanced.coroutine.flow.overview.ArticleHeadline
import com.example.androiddevkotlin.advanced.coroutine.flow.overview.NewsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

class NewsRemoteDataSrc(
    newsApi: NewsApi,
    private val externalScope: CoroutineScope
) {
    val latestNews: Flow<List<ArticleHeadline>> = flow {
        emit(newsApi.fetchLatestNews())
    }.shareIn(
        externalScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )
}