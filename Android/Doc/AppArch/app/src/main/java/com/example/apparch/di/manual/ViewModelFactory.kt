package com.example.apparch.di.manual

interface Factory<T> {
    fun create(): T
}

class LoginViewModelFactory(private val userRepository: UserRepository) : Factory<LoginViewModel> {
    override fun create(): LoginViewModel = LoginViewModel(userRepository)
}