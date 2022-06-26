package com.example.androiddevkotlin.advanced.coroutine.flow.overview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androiddevkotlin.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class MyActivity: AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempFlow = flow<Int> {
            var num = 0
            while (true) {
                emit(num++)
                delay(1000)
            }
        }

        lifecycleScope.launch {
            launch {
                tempFlow.collect { num ->
                    Log.d("MyActivity", "collect $num --------")
                }
            }
            delay(2_000)
            launch {
                tempFlow.collect { num ->
                    Log.d("MyActivity", "collect $num")
                }
            }
        }
    }

}