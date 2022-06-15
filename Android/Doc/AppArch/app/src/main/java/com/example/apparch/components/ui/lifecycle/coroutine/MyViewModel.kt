package com.example.apparch.components.ui.lifecycle.coroutine

import android.icu.util.TimeUnit
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Duration

class MyViewModel : ViewModel() {

    val someDataFlow: Flow<String> = flow { emit("data") }
    val someDataFlow2: Flow<String> = flow {  }

    private val userId: LiveData<String> = MutableLiveData()
    val user = userId.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(DataBase().loadUserById(id))
        }
    }
}