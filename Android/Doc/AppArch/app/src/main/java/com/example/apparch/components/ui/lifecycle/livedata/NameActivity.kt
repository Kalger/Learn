package com.example.apparch.components.ui.lifecycle.livedata

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class NameActivity : AppCompatActivity() {

    private val model by viewModels<NameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nameObserver = Observer<String> { newName ->
            val textView = TextView(this)
            textView.text = newName
        }

        model.curName.observe(this, nameObserver)

        Button(this).setOnClickListener {
            model.curName.value = "John"
        }
    }
}