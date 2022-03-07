package com.example.activitytest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.activitytest.databinding.SecondLayoutBinding

class SecondActivity : BaseActivity() {

    lateinit var binding: SecondLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "task id is $taskId")
        binding = SecondLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            case354()
//            case352()
//            case335()
        }
//        case334()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    private fun case354() {
        val intent = Intent(this, ThirdActivity::class.java)
        startActivity(intent)
    }

    private fun case352() {
        val intent = Intent(this, FirstActivity::class.java)
        startActivity(intent)
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
//        case335()
        super.onBackPressed()
    }

    companion object {
        const val TAG = "SecondActivity"

        fun actionStart(ctx: Context, data1: String, data2: String) {
            val intent = Intent(ctx, SecondActivity::class.java).apply {
                putExtra("key1", data1)
                putExtra("key2", data2)
            }
            ctx.startActivity(intent)
        }

    }
}