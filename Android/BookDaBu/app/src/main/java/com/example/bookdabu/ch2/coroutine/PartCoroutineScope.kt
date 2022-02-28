package com.example.bookdabu.ch2.coroutine

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.bookdabu.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class AActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // case 1
        GlobalScope.launch {
            updateWithDelay()
        }
        updateWithoutDelay()

        // case2
        lifecycleScope.launchWhenCreated {

        }
    }
}

class CorFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {  }
    }
}

class CorViewModel : ViewModel() {
    init {
        viewModelScope.launch {
        }
    }
}