package com.example.androiddevkotlin.advanced.coroutine.intro

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

class LoginRepository(private val responseParser: LoginResponseParser) {
    companion object {
        private const val loginUrl = "https://example.com/login"
    }

    suspend fun makeLoginRequest(jsonBody: String) = withContext(Dispatchers.IO) {
        val url = URL(loginUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
            outputStream.write(jsonBody.toByteArray())
            return@withContext Result.Success(responseParser.parse(inputStream))
        }
        return@withContext Result.Error(Exception("Cannot open HttpURLConnection"))
    }
}

// class created to avoid compiler error
class LoginResponseParser {
    fun parse(stream: InputStream): LoginResponse {
        return LoginResponse("Alger")
    }
}

// class created to avoid compiler error
data class LoginResponse(
    val name: String
)