package com.example.apparch.guide.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException

data class NewsUiState(
    val isSignedIn: Boolean = false,
    val isPremium: Boolean = false,
    val newsItem: List<NewsItemUiState> = listOf(),
    val userMessages: List<Message> = listOf(),
    val isFetchingArticle: Boolean = false
)

val NewsUiState.canBookmarkNews: Boolean get() = isSignedIn && isPremium

data class NewsItemUiState(
    val title: String,
    val body: String,
    val bookmarked: Boolean = false
)

data class Message(val id: Long, val message: String)

class NewsViewModel(
    private val repo: NewsRepo
): ViewModel() {
    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun fetchArticles(category: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                val newsItem = repo.newsItemsForCategory(category)
                _uiState.update {
                    it.copy(newsItem =  newsItem)
                }
            } catch (ioe: IOException) {
                _uiState.update {
                    val messages = getMessagesFromThrowable(ioe)
                    it.copy(userMessages = messages)
                }
            }

        }
    }

}

class NewsActivity : AppCompatActivity() {
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    // Update UI elements
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .map { it.isFetchingArticle }
                    .distinctUntilChanged()
                    .collect { progressbar.isVisible = it }
            }
        }
    }
}