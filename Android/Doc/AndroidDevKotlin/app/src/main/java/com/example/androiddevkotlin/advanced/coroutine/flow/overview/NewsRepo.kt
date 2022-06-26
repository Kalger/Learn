package com.example.androiddevkotlin.advanced.coroutine.flow.overview

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

class NewsRepo(
    newsRemoteDataSrc: NewsRemoteDataSrc,
    userData: UserData,
    defaultDispatcher: CoroutineDispatcher
) {

    val favoriteLatestNews: Flow<List<ArticleHeadline>> =
        newsRemoteDataSrc.latestNews
            .map { news -> news.filter { userData.isFavoriteTopic(it) } }
            .onEach { cacheNews(it) }
            // flowOn affects the upstream flow ↑
            .flowOn(defaultDispatcher)
            // the downstream flow ↓ is not affected
            .catch { emit(lastCacheNews()) }    // Executes in the consumer's context

    private fun lastCacheNews(): List<ArticleHeadline> {
        return listOf()
    }

    private fun cacheNews(it: List<ArticleHeadline>) {

    }
}

data class UserData(
    val name: String = "Yo"
) {
    fun isFavoriteTopic(news: ArticleHeadline): Boolean {
        return true
    }
}