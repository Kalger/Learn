package com.example.summit19.livedataflow

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.Exception
import javax.security.auth.callback.Callback
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class MySrc {

    private val api = APIJava()
    private val retrofitClient = RetrofitSimulation()

    fun fetchWeatherFlow() = flow<String> {  }

    fun fetchWeatherFlow2() = flow<String> {  }

    suspend fun doOneShot() {
        retrofitClient.doSomething("param")
    }

    // 如果您的數據源尚未支持協程，比如是一個 Java 代碼庫，而且使用的是回調機制。這時您可以使用 suspendCancellableCoroutine 協程構造方法
    suspend fun doOneShotWithJavaLib(): Result<String> =
    suspendCancellableCoroutine { continuation ->
        api.addOnCompleteListener { result ->
            continuation.resume(result)
        }.addOnFailureListener {
            continuation.resumeWithException(it)
        }.fetchSomething("test")
    }

    class RetrofitSimulation {
        fun doSomething(id: String) {}
    }

    class APIJava {
        fun addOnCompleteListener(block: (Result<String>) -> Unit): APIJava {
            return this
        }
        fun addOnFailureListener(block: (Exception) -> Unit): APIJava {
            return this
        }
        fun fetchSomething(param: String) {

        }

    }

    fun fetchWeatherFlow3() = flow<String> {
        while (true) {
            delay(2_000)
            emit("good")
        }
    }

    // 如果開發者使用的是不支持 Flow 而是使用回調的代碼庫，則可以使用 callbackFlow。
    fun caseCallBackApiWithFlow(api: CallbackBasedApi): Flow<String> = callbackFlow {
        val callback = object : CallbackBasedApi.CallBack {
            override fun onNextValue(value: String) {
                trySend(value)
            }

            override fun onApiError(cause: Throwable) {
                close(cause)
            }

            override fun onCompleted() = close()
        }

        api.register(callback)

        // Suspends the current coroutine until the channel is either closed or cancelled
        // and invokes the given block before resuming the coroutine. 如果沒有透過 awaitClose 來 suspend
        // Flow 就關閉了
        awaitClose { api.unRegister(callback) }
    }

    class CallbackBasedApi {

        fun register(callback: CallBack) {

        }

        fun unRegister(callback: CallBack) {

        }

        interface CallBack {
            fun onNextValue(value: String)
            fun onApiError(cause: Throwable)
            fun onCompleted(): Boolean
        }
    }

}