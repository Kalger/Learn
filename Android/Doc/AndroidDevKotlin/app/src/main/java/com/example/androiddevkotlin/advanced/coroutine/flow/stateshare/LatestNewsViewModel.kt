package com.example.androiddevkotlin.advanced.coroutine.flow.stateshare

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevkotlin.advanced.coroutine.flow.overview.ArticleHeadline
import com.example.androiddevkotlin.advanced.coroutine.flow.overview.NewsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LatestNewsViewModel(
    newsRepo: NewsRepo
): ViewModel(
) {
    private val _uiState = MutableStateFlow(LatestNewsUiState.Success(emptyList()))
    val uiState: StateFlow<LatestNewsUiState> = _uiState

    init {
        viewModelScope.launch {
            newsRepo.favoriteLatestNews.collect { news ->
                _uiState.value = LatestNewsUiState.Success(news)
            }
        }
    }

}

sealed class LatestNewsUiState{
    data class Success(val news: List<ArticleHeadline>) : LatestNewsUiState()
    data class Exception(val exception: Throwable) : LatestNewsUiState()
}