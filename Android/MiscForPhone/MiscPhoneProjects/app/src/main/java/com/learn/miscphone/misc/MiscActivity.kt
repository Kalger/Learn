package com.learn.miscphone.misc

import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.learn.miscphone.R

class MiscActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MiscActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_misc)

    }

    // for ',' in a number
    fun numberFormatUsage() {
        val formattedTip = NumberFormat.getNumberInstance().format(1296)
        Log.d(TAG, formattedTip)
    }
}