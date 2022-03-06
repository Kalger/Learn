package com.example.activitytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.activitytest.databinding.FirstLayoutBinding
import com.example.activitytest.databinding.ThirdLayoutBinding

class ThirdActivity : BaseActivity() {
    lateinit var binding: ThirdLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "task id is $taskId")
        binding = ThirdLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button3.setOnClickListener {
            ActivityCollector.finishAll()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    companion object {
        const val TAG = "ThirdActivity"
    }
}