package com.example.apparch

object FakeCrashLibrary {
    fun log(priority: Int, tag: String?, message: String?) {
    }

    fun logWarning(t: Throwable?) {
    }

    fun logError(t: Throwable?) {
    }
}
