package com.example.androiddevkotlin.advanced.coroutine.intro

import android.view.Display
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Just a doc example, could not execute
 */
class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    fun login(userName: String, token: String) {
        // Create a new coroutine on the UI thread

        viewModelScope.launch {

            val jsonBody = "userName: \"$userName\", token: \"$token\""

            // Make the network call and suspend execution of coroutine until it finishes
            val result = try {
                loginRepository.makeLoginRequest(jsonBody)
            } catch (e: Exception) {
                Result.Error(Exception("Network request failed"))
            }

            // Display result of the network request to the user
            when(result) {
                is Result.Success<LoginResponse> -> {
                    // Good
                }
                is Result.Error -> {
                    // error
                }
            }
        }
    }
}