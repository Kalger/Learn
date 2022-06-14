package com.example.apparch.components.ui.lifecycle.livedata

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class MyFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        case1()
        case2()

    }

    private fun case2() {
        StockLiveData.getInstance("00878").observe(viewLifecycleOwner) { price ->
            // update UI
        }
    }

    private fun case1() {
        val myPriceListener = StockLiveData("00878")
        myPriceListener.observe(viewLifecycleOwner) { price ->
            // update UI
        }
    }
}