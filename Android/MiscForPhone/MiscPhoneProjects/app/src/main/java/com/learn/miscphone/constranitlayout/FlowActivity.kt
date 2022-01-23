package com.learn.miscphone.constranitlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import com.learn.miscphone.R
import com.learn.miscphone.databinding.ActivityFlowBinding

class FlowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            val curFragment = supportFragmentManager.findFragmentById(binding.container.id)
            if (curFragment !is FlexBoxFragment) {
                supportFragmentManager.beginTransaction().run {
                    replace(binding.container.id, FlexBoxFragment())
                    commit()
                }
            } else {
                supportFragmentManager.beginTransaction().run {
                    replace(binding.container.id, GridFragment())
                    commit()
                }
            }
        }
    }
}