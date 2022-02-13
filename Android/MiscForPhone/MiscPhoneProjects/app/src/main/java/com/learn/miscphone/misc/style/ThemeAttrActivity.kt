package com.learn.miscphone.misc.style

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learn.miscphone.R

class ThemeAttrActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MiscPhone_Test)
        setContentView(R.layout.activity_theme_attr)
    }
}