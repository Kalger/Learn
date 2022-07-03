package com.example.androiddevkotlin.advanced.coroutine.flow.stateshare

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class TickHandler(
    private val externalScope: CoroutineScope,
    private val tickIntervalMs: Long = 5000
) {

    private val _tickFlow = MutableSharedFlow<Unit>()
    val tickFlow: SharedFlow<Unit> = _tickFlow
    init {
        externalScope.launch {
            while (true) {
                _tickFlow.emit(Unit)
                delay(tickIntervalMs)
            }
        }
    }
}
