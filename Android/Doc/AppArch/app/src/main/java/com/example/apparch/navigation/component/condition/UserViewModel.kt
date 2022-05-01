package com.example.apparch.navigation.component.condition

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _user = MutableLiveData<String?>()
    val user: LiveData<String?> = _user

    init {
        viewModelScope.launch {
            delay(1000)
            _user.value = null
        }
    }

    fun login(success: Boolean): LiveData<Result> {

        return if (success) {
            _user.value = "login"
            MutableLiveData(Result(true))
        } else {
            _user.value = null
            MutableLiveData(Result(false))
        }
    }

    data class Result(
        val success: Boolean = false
    )
}