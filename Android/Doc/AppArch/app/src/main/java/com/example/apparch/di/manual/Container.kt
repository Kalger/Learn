package com.example.apparch.di.manual

import retrofit2.Retrofit

class AppContainer {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://example.com")
        .build()
        .create(LoginService::class.java)
    private val remoteDataSource = UserRemoteDataSource(retrofit)
    private val localDataSource = UserLocalDataSource()

    val userRepository = UserRepository(localDataSource, remoteDataSource)
    val loginViewModelFactory = LoginViewModelFactory(userRepository)
}

class AppContainerWithFlow {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://example.com")
        .build()
        .create(LoginService::class.java)
    private val remoteDataSource = UserRemoteDataSource(retrofit)
    private val localDataSource = UserLocalDataSource()

    val userRepository = UserRepository(localDataSource, remoteDataSource)
    var loginContainer: LoginContainer? = null
}

class LoginContainer(userRepository: UserRepository) {
    val userLoginData = UserLoginData()
    val loginViewModelFactory = LoginViewModelFactory(userRepository)
}