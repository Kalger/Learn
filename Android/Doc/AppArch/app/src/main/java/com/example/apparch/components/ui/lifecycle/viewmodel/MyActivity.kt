package com.example.apparch.components.ui.lifecycle.viewmodel

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model by viewModels<MyViewModel>()

        model.getUsers().observe(this, Observer<List<User>> {

        })
    }
}