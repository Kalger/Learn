package com.example.apparch.guide

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class ExampleRepository(
    private val remoteDataSrc: ExampleRemoteDataSource,
    private val localDataSrc: ExampleLocalDataSource
) {
    val data: Flow<Example> =

    suspend fun modifyData(example: Example) {}
}

data class ArticleApiModel(
    val id: Long,
    val title: String,
    val content: String,
    val publicationDate: Date,
    val modifications: Array<ArticleApiModel>,
    val comments: Array<CommentApiModel>,
    val lastModificationDate: Date,
    val authorId: Long,
    val authorName: String,
    val authorDateOfBirth: Date,
    val readTimeMin: Int
)

data class Article(
    val id: Long,
    val title: String,
    val content: String,
    val publicationDate: Date,
    val authorName: String,
    val readTimeMin: Int
)

// network request
class NewsRemoteDataSrc(
    private val newsApi: NewsApi,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun fetchLatestNews(): List<ArticleHeadline> = withContext(ioDispatcher) {
        newsApi.fetchLatestNews()
    }
}

interface NewsApi {
    fun fetchLatestNews(): List<ArticleHeadline>
}

class NewsRepo(
    private val newsRemoteDataSrc: NewsRemoteDataSrc
) {
    suspend fun fetchLatestNews(): List<ArticleHeadline> = newsRemoteDataSrc.fetchLatestNews()
}

// cache
class NewsRepoCaseCache(
    private val newsRemoteDataSrc: NewsRemoteDataSrc,
    private val externalScope: CoroutineScope
) {
    private val latestNewsMutex = Mutex()

    private var latestNews: List<ArticleHeadline> = emptyList()

    suspend fun getLatestNews(refresh: Boolean = false): List<ArticleHeadline> {
        return if (refresh || latestNews.isEmpty()) {
            externalScope.async {
                newsRemoteDataSrc.fetchLatestNews().also { networkResult: List<ArticleHeadline> ->
                    latestNewsMutex.withLock { latestNews = networkResult }
                }
            }.await()
        } else  latestNewsMutex.withLock { latestNews }
    }
}

// WorkManager
class RefreshLatestNewsWorker(
    private val newsRepository: NewsReposiotry,
    content: Context,
    params: WorkerParameters
) : CoroutineWorker(content, params) {
    override suspend fun doWork(): Result = try {
        newsRepository.refreshLatestNews()
        Result.success()
    } catch (error: Throwable) {
        Result.failure()
    }
}


private const val REFRESH_RATE_HOURS = 4L
private const val FETCH_LATEST_NEWS_TASK = "FetchLatestNewsTask"
private const val TAG_FETCH_LATEST_NEWS = "FetchLatestNewsTaskTag"

class NewsTaskDataSource(
    private val workManager: WorkManager
) {
    @RequiresApi(Build.VERSION_CODES.R)
    fun fetchNewsPeriodically() {
        val fetchNewsRequest = PeriodicWorkRequestBuilder<RefreshLatestNewsWorker>(
            REFRESH_RATE_HOURS, TimeUnit.HOURS
        ).setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.TEMPORARILY_UNMETERED)
                .setRequiresCharging(true)
                .build()
        ).addTag(TAG_FETCH_LATEST_NEWS)

        workManager.enqueueUniquePeriodicWork(
            FETCH_LATEST_NEWS_TASK,
            ExistingPeriodicWorkPolicy.KEEP,
            fetchNewsRequest.build()
        )

        fun cancelFetchNewsPeriodically() {
            workManager.cancelAllWorkByTag(TAG_FETCH_LATEST_NEWS)
        }
    }
}