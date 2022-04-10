package com.learn.miscphone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learn.miscphone.constranitlayout.FlowActivity
import com.learn.miscphone.databinding.ActivityMainBinding
import com.learn.miscphone.misc.MiscActivity
import com.learn.miscphone.misc.style.ThemeAttrActivity
import com.learn.miscphone.misc.xmlClick.XmlClickActivity
import com.learn.miscphone.permissions.RequestActivity
import com.learn.miscphone.template.basicactivity.BasicActivity
import com.learn.miscphone.template.navdraweractivity.NavDrawerActivity

/**
 * use ListFragment
 * referenced from https://codertw.com/android-%E9%96%8B%E7%99%BC/349301/
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}