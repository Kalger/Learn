package com.example.activitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.activitytest.databinding.SecondLayoutBinding

class SecondActivity : AppCompatActivity() {

    lateinit var binding: SecondLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener { case335() }
//        case334()
    }

    private fun case335() {
        val intent = Intent()
        intent.putExtra("data_return", "I'm come back")
        // 這個 activity 銷毀後，才會呼叫 parent activity 的 onActivityResult
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun case334() {
        val extra = intent.getStringExtra("extra_data")
        Log.d("SecondActivity", "extra data is $extra")
    }

    override fun onBackPressed() {
        case335()
        super.onBackPressed()
    }
}