package com.example.blog.livedata2flow

import android.service.autofill.UserData
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// #1
class MyViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow<Result<UiState>>(Result.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = Result<UiState>()
            _uiState.update { result }
        }
    }
}

class Result<T> () {
    companion object {
        val Loading = Result<UiState>()
        val LoadingUser = Result<UiState>()
    }
}

class UiState() {

}

// #2
class MyViewModel2(repository: MyRepository) : ViewModel() {
    val result: LiveData<Result<UiState>> = liveData {
        emit(Result.Loading)
        emit(repository.fetchItem())
    }

    val result2: StateFlow<Result<UiState>> = flow {
        emit(repository.fetchItem())
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Result.Loading
    )
}

class MyRepository {
    suspend fun fetchItem(): Result<UiState> = Result<UiState>()
    suspend fun fetchItem(id: String): Result<UiState> = Result<UiState>()
    fun observeItem(id: String): Flow<Result<UiState>> = flow {  }
}

// #3
class MyViewModel3(repository: MyRepository, authManager: AuthManager) : ViewModel() {
    private val userId: LiveData<String> = authManager.observeUser().map { user -> user.id }.asLiveData()
    val result: LiveData<Result<UiState>> = userId.switchMap { id ->
        liveData { emit(repository.fetchItem(id)) }
    }

    private val userId2 = authManager.observeUser().map { user -> user.id }
    @OptIn(ExperimentalCoroutinesApi::class)
    val result2: LiveData<Result<UiState>> = userId2.mapLatest {
            newUserId -> repository.fetchItem(newUserId)
    }.asLiveData()

    // state flow
    private val userId3: Flow<String> = authManager.observeUser().map { user -> user.id }
    val result3: StateFlow<Result<UiState>> = userId3.mapLatest { newUserId ->
        repository.fetchItem(newUserId)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Result.Loading
    )
    @OptIn(ExperimentalCoroutinesApi::class)
    val result4: StateFlow<Result<UiState>> = userId3.transformLatest { newUserId ->
        emit(Result.Loading)
        emit(repository.fetchItem(newUserId))
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Result.LoadingUser
    )
}

class Item {
}

class AuthManager {
    fun observeUser(): Flow<UserData> = flow {  }
}

// #4

class MyViewModel4(repository: MyRepository, authManager: AuthManager) : ViewModel() {
    // liveData
    private val userId: LiveData<String> = authManager.observeUser().map { user -> user.id }
        .asLiveData()
    val result: LiveData<Result<UiState>> = userId.switchMap { id ->
        repository.observeItem(id).asLiveData()
    }

    private val userId2: Flow<String> = authManager.observeUser().map { user -> user.id }
    val result2: LiveData<Result<UiState>> = userId2.flatMapLatest { repository.observeItem(it) }
        .asLiveData()

    // stateFlow
    val result3: StateFlow<Result<UiState>> = userId2.flatMapLatest {
        repository.observeItem(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Result.LoadingUser
    )
}

// #5
class MyViewModel5() : ViewModel() {
    // liveData
    val liveData1: LiveData<Int> = MutableLiveData<Int>()
    val liveData2: LiveData<Int> = MutableLiveData<Int>()

    val result = MediatorLiveData<Int>()

    fun combineLiveData() {
        result.addSource(liveData1) { value ->
            result.value = value + (liveData2.value ?: 0)
        }
        result.addSource(liveData2) { value ->
            result.value = value + (liveData1.value ?: 0)
        }
    }

    // flow
    private val flow1: Flow<Int> = flow {  }
    private val flow2: Flow<Int> = flow {  }
    val result2 = combine(flow1, flow2) { v1, v2 -> v1 + v2 }
    val result3 = flow1.combineTransform(flow2) { v1, v2 -> emit(v1 + v2) }
    val result4 = flow1.zip(flow2) { v1, v2 -> v1 + v2 }

}