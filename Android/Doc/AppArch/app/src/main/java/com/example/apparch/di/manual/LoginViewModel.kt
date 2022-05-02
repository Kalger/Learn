package com.example.apparch.di.manual

import androidx.lifecycle.ViewModel

class LoginViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

}