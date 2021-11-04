package com.course.fragmenttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<TextView>(R.id.button)
        button.setOnClickListener {
            replaceFragment(AnotherRightFragment())
        }
        replaceFragment(RightFragment())
    }

    private fun replaceFragment(frag: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.rightLayout, frag)
//            // because addToBackStack, fragment that is removed or replaced would enter
//            // created state (just onDestroyView instead of onDestroy)
//            .addToBackStack(null)
//            .commit()
    }
}