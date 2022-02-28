package com.example.bookdabu.ch2.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


fun caseCancel() {
    val job = GlobalScope.launch {
        updateWithDelay()
    }
    job.cancel()
}

fun caseIsActive() {
    GlobalScope.launch {
        if (isActive) {
            someCalculation()
        }
    }
}

fun caseReleaseWhenCancelException() {
    GlobalScope.launch {
        try {
            someCalculation()
        } finally {
            // release resource
        }
    }
}


