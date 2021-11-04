package com.learn.miscphone.misc.xmlClick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.learn.miscphone.R
import com.learn.miscphone.misc.MiscActivity

class XmlClickActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_click)
    }


    fun onRadioBtnClick(view: View) {
        Log.d(TAG, "onRadioBtnClick")
    }

    companion object {
        private const val TAG = "XmlClickActivity"
    }

    fun buttonClick(view: View) {
        Log.d(TAG, "buttonClick")
    }
}