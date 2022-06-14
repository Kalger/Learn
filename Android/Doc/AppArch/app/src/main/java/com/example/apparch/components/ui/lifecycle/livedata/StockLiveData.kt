package com.example.apparch.components.ui.lifecycle.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import java.math.BigDecimal

class StockLiveData(symbol: String) : LiveData<BigDecimal>() {

    private val stockManger = StockManager(symbol)

    private val listener = { price: BigDecimal ->
        value = price
    }

    override fun onActive() {
        stockManger.requestPriceUpdate(listener)
    }

    override fun onInactive() {
        stockManger.removeUpdates(listener)
    }

    companion object {
        private lateinit var sInstance: StockLiveData

        @MainThread
        fun getInstance(symbol: String): StockLiveData {
            // :: => https://kotlinlang.org/docs/reflection.html#property-references
            return if (::sInstance.isInitialized) sInstance else StockLiveData(symbol)
        }
    }

}

class StockManager(symbol: String) {

    fun requestPriceUpdate(listener : (BigDecimal) -> Unit) {

    }

    fun removeUpdates(listener : (BigDecimal) -> Unit) {

    }
}
