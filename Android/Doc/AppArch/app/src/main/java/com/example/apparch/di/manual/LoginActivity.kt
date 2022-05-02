package com.example.apparch.di.manual

import android.app.Activity
import android.os.Bundle
import retrofit2.Retrofit

class LoginActivity : Activity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginData: UserLoginData
    private lateinit var appContainerWithFlow: AppContainerWithFlow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        caseBasic()
//        caseAppContainer()
//        caseAppContainerWithViewModelFactory()
        caseAppContainerWithFlow()
    }

    override fun onDestroy() {
        appContainerWithFlow.loginContainer = null
        super.onDestroy()
    }

    private fun caseAppContainerWithFlow() {
        val appContainerWithFlow = (application as MyApplication).appContainerWithFlow
        appContainerWithFlow.loginContainer = LoginContainer(appContainerWithFlow.userRepository)
        loginViewModel = appContainerWithFlow.loginContainer!!.loginViewModelFactory.create()
        loginData = appContainerWithFlow.loginContainer!!.userLoginData
    }

    private fun caseAppContainerWithViewModelFactory() {
        val appContainer = (application as MyApplication).appContainer
        loginViewModel = appContainer.loginViewModelFactory.create()
    }

    private fun caseAppContainer() {
        val appContainer = (application as MyApplication).appContainer
        loginViewModel = LoginViewModel(appContainer.userRepository)
    }

    private fun caseBasic() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(LoginService::class.java)

        val remoteDataSource = UserRemoteDataSource(retrofit)
        val localDataSource = UserLocalDataSource()

        val userRepository = UserRepository(localDataSource, remoteDataSource)
        loginViewModel = LoginViewModel(userRepository)
    }
}