package com.learn.miscphone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learn.miscphone.constranitlayout.FlowActivity
import com.learn.miscphone.databinding.ActivityMainBinding
import com.learn.miscphone.misc.xmlClick.XmlClickActivity
import com.learn.miscphone.permissions.RequestActivity
import com.learn.miscphone.template.basicactivity.BasicActivity
import com.learn.miscphone.template.navdraweractivity.NavDrawerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.permission.setOnClickListener {
            startActivity(Intent(this, RequestActivity::class.java))
        }

        binding.basicButton.setOnClickListener {
            startActivity(Intent(this, BasicActivity::class.java))
        }

        binding.navDrawerButton.setOnClickListener {
            startActivity(Intent(this, NavDrawerActivity::class.java))
        }

        binding.constraintFlowButton.setOnClickListener {
            startActivity(Intent(this, FlowActivity::class.java))
        }

        binding.miscButton.setOnClickListener {
            startActivity(Intent(this, XmlClickActivity::class.java))
        }
    }
}