package com.example.apparch.di.manual

import android.app.Application

class MyApplication : Application() {
    val appContainer = AppContainer()
    val appContainerWithFlow = AppContainerWithFlow()
}