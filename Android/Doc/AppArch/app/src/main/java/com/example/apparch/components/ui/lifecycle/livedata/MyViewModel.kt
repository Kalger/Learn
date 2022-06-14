package com.example.apparch.components.ui.lifecycle.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlin.reflect.KProperty

class MyViewModel(private val repo: PostalCodeRepo) : ViewModel() {
    private val addressInput = MutableLiveData<String>()

    // 如果 repo 返回 livedata，應該這樣做，避免每次取得不同的 livedata
    val postalCode: LiveData<String> = Transformations.switchMap(addressInput) { address ->
        repo.getPostalCode(address)
    }

    private fun setInput(address: String) {
        addressInput.value = address
    }

}


class PostalCodeRepo {

    fun getPostalCode(address: String): LiveData<String> = MutableLiveData<String>()
}
