package com.example.activitytest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        Log.d(TAG, this.toString())
        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            case352()
//            case351()
//            case335()
//            case334()
//            case333Dial()
//            case333View()
//            case332()
//            case331()
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    private fun case352() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun case351() {
        val intent = Intent(this, FirstActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val returnData = data?.getStringExtra("data_return")
                Log.d(TAG, "return is $returnData")
            }

        }
    }

    private fun case335() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivityForResult(intent, 1)
    }

    private fun case334() {
        val data = "Hello"
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("extra_data", data)
        startActivity(intent)
    }

    private fun case333Dial() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:10086")
        startActivity(intent)

    }

    private fun case333View() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.baidu.com")
        startActivity(intent)
    }

    private fun case332() {
        // Intent constructor style 2
        val intent = Intent("com.example.activitytest.ACTION_START")
        intent.addCategory("com.example.activitytest.MY_CATEGORY")
        startActivity(intent)
    }

    // chapter 3.3.1
    private fun case331() {
        // Intent constructor style 1
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add_item -> Toast.makeText(this, "add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    companion object {
        const val TAG = "FirstActivity"
    }
}