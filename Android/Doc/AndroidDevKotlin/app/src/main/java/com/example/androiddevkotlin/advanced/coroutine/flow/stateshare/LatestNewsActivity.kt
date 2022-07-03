package com.example.androiddevkotlin.advanced.coroutine.flow.stateshare

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.androiddevkotlin.advanced.coroutine.flow.overview.ArticleHeadline
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LatestNewsActivity : AppCompatActivity() {

    private val latestNewsViewModel by viewModels<LatestNewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                latestNewsViewModel.uiState.collect { state ->
                    when(state) {
                        is LatestNewsUiState.Success -> showNews(state.news)
                        is LatestNewsUiState.Exception -> showError(state.exception)
                    }
                }
            }
        }
    }

    private fun showError(exception: Throwable) {
        
    }

    private fun showNews(news: List<ArticleHeadline>) {

    }

}