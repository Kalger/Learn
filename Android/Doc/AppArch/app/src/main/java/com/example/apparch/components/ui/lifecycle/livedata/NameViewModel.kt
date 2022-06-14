package com.example.apparch.components.ui.lifecycle.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel() {
    val curName: MutableLiveData<String> by lazy { MutableLiveData() }
}