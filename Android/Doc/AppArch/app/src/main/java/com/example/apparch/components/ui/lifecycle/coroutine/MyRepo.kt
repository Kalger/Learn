package com.example.apparch.components.ui.lifecycle.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.apparch.components.ui.lifecycle.viewmodel.User
import kotlinx.coroutines.delay
import java.io.IOException
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class UserDao {
    fun getUser(id: String): LiveData<User> {
        return MutableLiveData()
    }

    fun insert(user: User) {}
}

class MyRepo {

    fun getUser(id: String) = liveData<Result> {
        val disposable = emitSource(
            UserDao().getUser(id).map {
                Result.loading(it)
            }
        )

        try {
            val user = WebService().fetchUser(id)
            disposable.dispose()
            UserDao().insert(user)
            emitSource(
                UserDao().getUser(id).map {
                    Result.success(it)
                }
            )
        } catch (exception: IOException) {
            emitSource(
                UserDao().getUser(id).map {
                    Result.error(exception, it)
                }
            )
        }
    }
}

class WebService {
    suspend fun fetchUser(id: String): User {
      delay(1.seconds)
      return User()
    }
}

