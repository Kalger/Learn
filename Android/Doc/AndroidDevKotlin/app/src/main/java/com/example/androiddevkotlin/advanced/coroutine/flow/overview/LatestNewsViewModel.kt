package com.example.androiddevkotlin.advanced.coroutine.flow.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LatestNewsViewModel(
    newsRepo: NewsRepo
): ViewModel(
) {
    init {
        viewModelScope.launch {
            newsRepo.favoriteLatestNews
                .catch { exception ->  notifyError(exception) }
                .collect {

                }
        }
    }

    private fun notifyError(exception: Throwable) {

    }
}