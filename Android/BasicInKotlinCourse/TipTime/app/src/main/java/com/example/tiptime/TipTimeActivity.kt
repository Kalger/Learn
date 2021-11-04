package com.example.tiptime

import android.content.Context
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityTipTimeBinding

class TipTimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTipTimeBinding

    val testFun: (View, Int, KeyEvent) -> Boolean = {
            v, keyCode, _ -> handleKeyEvent(v, keyCode)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
//        binding.costOfServiceEditText.setOnKeyListener(testFun)
        binding.costOfServiceEditText.setOnKeyListener {v, keyCode, _ -> handleKeyEvent(v, keyCode)}
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            Log.d("TipTimeActivity", "handleKeyEvent KEYCODE_ENTER")
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}