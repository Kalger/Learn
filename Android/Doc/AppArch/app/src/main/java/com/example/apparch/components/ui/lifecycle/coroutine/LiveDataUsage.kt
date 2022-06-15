package com.example.apparch.components.ui.lifecycle.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.apparch.components.ui.lifecycle.viewmodel.User
import kotlinx.coroutines.delay

class LiveDataUsage {
    val user: LiveData<User> = liveData {
        val data = DataBase().loadUser()
        emit(data)
    }

    val user2: LiveData<Result> = liveData {
        emit(Result.loading())
        try {
            emit(Result.success(fetchUser()))
        } catch (ioException: Exception) {
            emit(Result.error(ioException))
        }
    }

    private fun fetchUser(): Result {
        return Result()
    }
}

class Result {

    companion object {
        fun loading(): Result = Result()
        fun loading(user: User): Result = Result()
        fun success(result: Result): Result = result
        fun success(user: User): Result = Result()
        fun error(exception: Exception): Result = Result()
        fun error(exception: Exception, user: User): Result = Result()
    }
}

class DataBase() {
    suspend fun loadUser(): User {
        delay(100)
        return User()
    }

    suspend fun loadUserById(id: String): User {
        delay(100)
        return User()
    }
}

