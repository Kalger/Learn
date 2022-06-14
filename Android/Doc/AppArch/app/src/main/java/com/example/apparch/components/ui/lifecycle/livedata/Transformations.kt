package com.example.apparch.components.ui.lifecycle.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.example.apparch.components.ui.lifecycle.viewmodel.User

val userLiveData: LiveData<User> = MutableLiveData()
val userName: LiveData<String> = Transformations.map(userLiveData) {
    user -> user.name
}
val userName2: LiveData<String> = userLiveData.map {
    user -> user.name
}

private fun getUser(id: String): LiveData<User> = MutableLiveData()
val userId: LiveData<String> = MutableLiveData()
val user: LiveData<User> = Transformations.switchMap(userId) { id -> getUser(id) }
